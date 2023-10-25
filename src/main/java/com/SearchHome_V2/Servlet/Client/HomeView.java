package com.SearchHome_V2.Servlet.Client;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "homeView", value = "/homeView")
public class HomeView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/client/homePage.jsp").forward(req,resp);
        req.getRequestDispatcher("WEB-INF/views/client/post/createPost_first.jsp").forward(req,resp);

    }
}
