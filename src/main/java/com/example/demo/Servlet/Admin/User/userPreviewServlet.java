package com.example.demo.Servlet.Admin.User;

import com.example.demo.Service.UserService;
import com.example.demo.Utils.Protector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "userPreview",value = "/admin/userPreview")
public class userPreviewServlet extends HttpServlet {
    private UserService userService  = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L);
        String action = req.getParameter("action");
        System.out.println(action);

        String errorMessage = "Đã có lỗi truy vấn!";

        if ("HIDE".equals(action)) {
            String successMessage = String.format("Không duyệt #%s thành công!", id);
            Protector.of(() -> userService.hide(id))
                    .done(r -> req.getSession().setAttribute("successMessage", successMessage))
                    .fail(e -> req.getSession().setAttribute("errorMessage", errorMessage));
        }

        if ("SHOW".equals(action)) {
            String successMessage = String.format("Đã xác thực người dùng #%s thành công!", id);
            Protector.of(() -> userService.show(id))
                    .done(r -> req.getSession().setAttribute("successMessage", successMessage))
                    .fail(e -> req.getSession().setAttribute("errorMessage", errorMessage));
        }

        resp.sendRedirect(req.getContextPath() + "/admin");
    }

}
