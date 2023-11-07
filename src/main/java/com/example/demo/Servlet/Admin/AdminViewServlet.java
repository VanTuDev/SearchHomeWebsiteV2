package com.example.demo.Servlet.Admin;//package com.SearchHome_V2.Servlet.Admin;



import com.example.demo.Service.PostService;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminViewServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private final PostService postService = new PostService();
    private static final int USERS_PER_PAGE = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalUsers = Protector.of(userService::count).get(0);
        int totalPosts = Protector.of(postService::count).get(0);
        int totalPages = totalUsers / USERS_PER_PAGE + (totalUsers % USERS_PER_PAGE != 0 ? 1 : 0);

        String validParam = Optional.ofNullable(req.getParameter("valid")).orElse("101");
        int valid = Integer.parseInt(validParam);

        String pageParam = Optional.ofNullable(req.getParameter("page")).orElse("1");
        int page = Integer.parseInt(pageParam);
        if (page < 1 || page > totalPages) {
            page = 1;
        }

        int offset = (page - 1) * USERS_PER_PAGE;

        List<User> users = userService.getFilteredAndOrderedUsers(
                "EMPLOYEE", valid, "id", "DESC", USERS_PER_PAGE, offset
        );
        req.setAttribute("users", users);
        req.setAttribute("totalPosts", totalPosts);
        req.setAttribute("totalUsers", totalUsers);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/WEB-INF/views/admin/adminView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}