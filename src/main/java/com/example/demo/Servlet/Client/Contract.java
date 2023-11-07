package com.example.demo.Servlet.Client;


import com.example.demo.Service.PostService;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.Post;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "hopdong", value = "/hopdong")
public class Contract extends HttpServlet {
    private final PostService postService = new PostService();
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L);

        Optional<Post> postFromServer = Protector.of(() -> postService.getById(id)).get(Optional::empty);
        if (postFromServer.isPresent()){
            Post post = postFromServer.get();
            System.out.println(post);
            Optional<User> userFromServer = userService.getByUserId(post.getId());
            if (userFromServer.isPresent()){
                req.setAttribute("user",userFromServer.get());
                req.setAttribute("post", postFromServer.get());
                req.getRequestDispatcher("/WEB-INF/views/client/contract.jsp").forward(req, resp);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/homeView");


    }
}
