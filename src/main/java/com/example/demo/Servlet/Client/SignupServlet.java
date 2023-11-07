package com.example.demo.Servlet.Client;


import com.example.demo.Service.UserService;
import com.example.demo.Utils.Protector;
import com.example.demo.Utils.Validator;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/client/signupView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> values = new HashMap<>();
        values.put("username", request.getParameter("username"));
        values.put("password", request.getParameter("password"));
        values.put("fullname", request.getParameter("fullname"));
        values.put("email", request.getParameter("email"));
        values.put("phoneNumber", request.getParameter("phoneNumber"));
        values.put("gender", request.getParameter("gender"));
        values.put("cccd", request.getParameter("cccd"));
        values.put("policy", request.getParameter("policy"));
        values.put("image",request.getParameter("image"));

        Map<String, List<String>> violations = new HashMap<>();
        Optional<User> userFromServer = Protector.of(() -> userService.getByUsername(values.get("username")))
                .get(Optional::empty);
        violations.put("usernameViolations", Validator.of(values.get("username"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(25)
                .isNotExistent(userFromServer.isPresent(), "Tên đăng nhập")
                .toList());
        violations.put("passwordViolations", Validator.of(values.get("password"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(32)
                .toList());
        violations.put("fullnameViolations", Validator.of(values.get("fullname"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .toList());
        violations.put("emailViolations", Validator.of(values.get("email"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .hasPattern("^[^@]+@[^@]+\\.[^@]+$", "email")
                .toList());
        violations.put("phoneNumberViolations", Validator.of(values.get("phoneNumber"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .hasPattern("^\\d{10,11}$", "số điện thoại")
                .toList());
        violations.put("genderViolations", Validator.of(values.get("gender"))
                .isNotNull()
                .toList());
        violations.put("cccdViolations", Validator.of(values.get("cccd"))
                .isNotNullAndEmpty()
                .toList());
        violations.put("policyViolations", Validator.of(values.get("policy"))
                .isNotNull()
                .toList());

        // Tính tổng các vi phạm sau kiểm tra (nếu có)
        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Đã đăng ký thành công!";
        String errorMessage = "Đã có lỗi truy vấn!";

        // Khi không có vi phạm trong kiểm tra các parameter
        if (sumOfViolations == 0) {
            User user = new User(
                    0L,
                    values.get("username"),
                    values.get("password"),
                    values.get("fullname"),
                    Integer.parseInt(values.get("gender")),
                    values.get("phoneNumber"),
                    values.get("email"),
                    values.get("cccd"),
                    "CUSTOMER",
                    values.get("image"),
                    "",
                    "",
                    101
            );

            Protector.of(() -> userService.insert(user))
                    .done(r -> request.setAttribute("successMessage", successMessage))
                    .fail(e -> {
                        request.setAttribute("values", values);
                        request.setAttribute("errorMessage", errorMessage);
                    });
        } else {
            request.setAttribute("values", values);
            request.setAttribute("violations", violations);
        }
        request.getRequestDispatcher("/WEB-INF/views/client/signupView.jsp").forward(request, response);

    }
}
