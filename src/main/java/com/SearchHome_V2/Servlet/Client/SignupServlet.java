package com.SearchHome_V2.Servlet.Client;

import com.SearchHome_V2.Service.UserService;
import com.SearchHome_V2.Utils.HashingUtils;
import com.SearchHome_V2.Utils.Protector;
import com.SearchHome_V2.Utils.Validator;
import com.SearchHome_V2.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet(name = "SignupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/client/signupView.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFullName(request.getParameter("fullName"));
        user.setGender(Integer.parseInt(request.getParameter("gender")));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        user.setEmail(request.getParameter("email"));
        user.setCccd(request.getParameter("cccd"));
        user.setRole(request.getParameter("role"));
        System.out.println(user);


        request.getRequestDispatcher("/WEB-INF/views/client/signupView.jsp").forward(request, response);

    }
}
