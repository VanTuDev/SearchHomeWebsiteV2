package com.example.demo.Servlet.Admin.User;


import com.example.demo.Service.UserService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "UserDetailServlet", value = "/admin/userManager/detail")
public class detailUserServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L);
        Optional<User> userFromServer = Protector.of(() -> userService.getById(id)).get(Optional::empty);

        if (userFromServer.isPresent()) {
            req.setAttribute("user", userFromServer.get());
            req.getRequestDispatcher("/WEB-INF/views/admin/user/userDetailView.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin/userManager");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
