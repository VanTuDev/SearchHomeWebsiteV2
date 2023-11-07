package com.example.demo.Servlet.Client;

import com.example.demo.Service.UserService;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@WebServlet(name = "updateToRenter",value = "/client/updateToRenter")
public class UpdateToRenter extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/views/client/updateView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");

        Map<String, String> values = new HashMap<>();
        values.put("username", req.getParameter("username"));
        values.put("fullname", req.getParameter("fullname"));
        values.put("gender", req.getParameter("gender"));
        values.put("phoneNumber", req.getParameter("phoneNumber"));
        values.put("email", req.getParameter("email"));
        values.put("cccd", req.getParameter("cccd"));
        values.put("bankAccount", req.getParameter("bankAccount"));
        values.put("nameBankAccount", req.getParameter("nameBankAccount"));
        

        User newUser = new User(
                user.getId(),
                values.get("username"),
                user.getPassword(),
                values.get("fullname"),
                Integer.parseInt(values.get("gender")),
                values.get("phoneNumber"),
                values.get("email"),
                values.get("cccd"),
                "EMPLOYEE",
                values.get("imageName"),
                values.get("bankAccount"),
                values.get("nameBankAccount"),
                101
        );




        String successMessage = "Cập nhật thành công! Bạn đã có thể đăng bài";
        String errorMessage = "Cập nhật không thành công! Vui lòng bổ sung đầy đủ thông tin";

        Optional<User> userWithNewUsername = userService.getByUsername(values.get("username"));

        if (!user.getUsername().equals(values.get("username")) && userWithNewUsername.isPresent()) {
            req.setAttribute("errorMessage", errorMessage);
            req.setAttribute("user", user);
        } else {
            userService.update(newUser);
            req.setAttribute("successMessage", successMessage);
            req.setAttribute("user", newUser);
            req.getSession().setAttribute("currentUser", newUser);
        }

        req.getRequestDispatcher("/WEB-INF/views/client/updateView.jsp").forward(req, resp);
    }
}
