package com.example.demo.Servlet.Client;

import com.example.demo.Service.PostService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.Post;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/postHistory")
public class PostHistory extends HttpServlet {
    private final PostService postService = new PostService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        List<Post> posts = postService.getAllPostByUserId(user.getId());
        req.setAttribute("post",posts);
        req.getRequestDispatcher("/WEB-INF/views/client/postHistoryView.jsp").forward(req, resp);

    }
}
