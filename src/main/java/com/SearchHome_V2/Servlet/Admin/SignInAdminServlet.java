
package com.SearchHome_V2.Servlet.Admin;


import com.SearchHome_V2.Service.UserService;
import com.SearchHome_V2.Utils.Protector;
import com.SearchHome_V2.Utils.Validator;
import com.SearchHome_V2.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;


@WebServlet(name = "SignInAdmInServlet", value = "/admin/signIn")
public class SignInAdminServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/signInAdminView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> values = new HashMap<>();
        values.put("username", request.getParameter("username"));
        values.put("password", request.getParameter("password"));

        Map<String, List<String>> violations = new HashMap<>();
        Optional<User> userFromServer = Protector.of(() -> userService.getByUsername(values.get("username")))
                .get(Optional::empty);
        violations.put("usernameViolations", Validator.of(values.get("username"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(25)
                .isExistent(userFromServer.isPresent(), "Tên đăng nhập")
                .toList());
        violations.put("passwordViolations", Validator.of(values.get("password"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(32)
                .isEqualTo(userFromServer.map(User::getPassword).orElse(""), "Mật khẩu")
                .toList());

        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();

        if (sumOfViolations == 0 && userFromServer.isPresent()) {
            User user = userFromServer.get();
            if (Arrays.asList("ADMIN", "EMPLOYEE").contains(user.getRole())) {
                request.getSession().setAttribute("currentUser", user);
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                String errorMessage = "Người dùng không có quyền đăng nhập Admin!";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/WEB-INF/views/admin/signInAdminView.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("values", values);
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/WEB-INF/views/admin/signInAdminView.jsp").forward(request, response);
        }
    }
}