package com.SearchHome_V2.Servlet.Admin;//package com.SearchHome_V2.Servlet.Admin;


import com.SearchHome_V2.Service.UserService;
import com.SearchHome_V2.Utils.Protector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminViewServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalUsers = Protector.of(userService::count).get(0);

        req.setAttribute("totalUsers", totalUsers);
        req.getRequestDispatcher("/WEB-INF/views/admin/adminView.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}