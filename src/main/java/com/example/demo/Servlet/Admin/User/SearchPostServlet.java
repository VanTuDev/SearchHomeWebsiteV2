package com.example.demo.Servlet.Admin.User;

import com.example.demo.Service.PostService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.Post;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchPostServlet extends HttpServlet {
    private final PostService postService = new PostService();

    private static final int POST_PER_PAGE = 1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> query = Optional.ofNullable(req.getParameter("q")).filter(s -> !s.trim().isEmpty());

        if (query.isPresent()) {
            String queryStr = query.get();

            int totalPost = Protector.of(() -> postService.countByQuery(queryStr)).get(0);
            int totalPages = totalPost / POST_PER_PAGE + (totalPost % POST_PER_PAGE != 0 ? 1 : 0);

            String pageParam = Optional.ofNullable(req.getParameter("page")).orElse("1");
            int page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
            if (page < 1 || page > totalPages) {
                page = 1;
            }

            int offset = (page - 1) * POST_PER_PAGE;

            List<Post> posts = Protector.of(() -> postService.getByQuery(
                    queryStr, POST_PER_PAGE, offset
            )).get(ArrayList::new);

            posts.forEach(product -> product.setTitle(product.getTitle()
                    .replaceAll("(?i)(" + queryStr + ")", "<b class='bg-warning'>$1</b>")));

            req.setAttribute("query", queryStr);
            req.setAttribute("totalPost", totalPost);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("page", page);
            req.setAttribute("posts", posts);
            req.getRequestDispatcher("/WEB-INF/views/client/searchView.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}