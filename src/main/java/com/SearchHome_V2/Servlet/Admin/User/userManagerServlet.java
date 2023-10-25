package com.SearchHome_V2.Servlet.Admin.User;

import com.SearchHome_V2.Service.UserService;
import com.SearchHome_V2.Utils.Protector;
import com.SearchHome_V2.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "UserManagerServlet", value = "/admin/userManager")
public class userManagerServlet extends HttpServlet {
    private final UserService userService = new UserService();
    private static final int USERS_PER_PAGE = 3;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int totalUsers = userService.count();
        int totalPages = totalUsers / USERS_PER_PAGE + (totalUsers % USERS_PER_PAGE != 0 ? 1 : 0);

        String pageParam = Optional.ofNullable(req.getParameter("page")).orElse("1");
        int page = Integer.parseInt(pageParam);
        if (page < 1 || page > totalPages) {
            page = 1;
        }

        int offset = (page - 1) * USERS_PER_PAGE;

        List<User> users =  userService.getOrderedPart(
                USERS_PER_PAGE, offset, "id", "DESC"
        );

        req.setAttribute("totalPages", totalPages);
        req.setAttribute("page", page);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/admin/userManagerView.jsp").forward(req,resp);
    }

}
