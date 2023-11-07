package com.example.demo.Servlet.Admin.User;


import com.example.demo.Service.UserService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
@WebServlet(name = "DeleteUserServlet", value = "/admin/userManager/delete")
public class deleteUserServlet extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L);
        Optional<User> userFromServer = Protector.of(() -> userService.getById(id)).get(Optional::empty);

        if (userFromServer.isPresent()) {
            String successMessage = String.format("Xóa người dùng #%s thành công!", id);
            String errorMessage = String.format("Xóa người dùng #%s thất bại!", id);

            Protector.of(() -> userService.delete(id))
                    .done(r -> req.getSession().setAttribute("successMessage", successMessage))
                    .fail(e -> req.getSession().setAttribute("errorMessage", errorMessage));
        }

        resp.sendRedirect(req.getContextPath() + "/admin/userManager");
    }
}
