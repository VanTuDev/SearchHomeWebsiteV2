package com.example.demo.Servlet.Client;

import com.example.demo.Service.PostService;
import com.example.demo.Utils.Protector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/updateStatus")
public class UpdateStatus extends HttpServlet {
    private final PostService postService = new PostService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L);
        String action = req.getParameter("action");
        if ("SHOW".equals(action)) postService.show(id);
        if ("HIDE".equals(action)) postService.updateStatus(id);
        resp.sendRedirect(req.getContextPath() + "/postHistory");








    }
}
