package com.example.demo.filter;

import com.example.demo.beans.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@WebFilter(filterName = "AuthorizationFilter", value = "/admin/*")
public class EmployeeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);


        String loginURI = request.getContextPath() + "/signIn";
        String clientURI404 = request.getContextPath() + "/client/401";

        Optional<String> userRole = Optional.ofNullable(session)
                .map(s -> (User) s.getAttribute("currentUser"))
                .map(User::getRole);


        boolean isEmployee = userRole.map("EMPLOYEE"::equals).orElse(false);
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        Stream<String> restrictedPathsForEmployee = Stream.of("/admin/userManager")
                .map(path -> request.getContextPath() + path);

        boolean isNotAccessibleForEmployee = restrictedPathsForEmployee.anyMatch(p -> request.getRequestURI().startsWith(p));

        if ( isEmployee || loginRequest) {
            if (isEmployee && isNotAccessibleForEmployee) {
                response.sendRedirect(clientURI404);
            } else {
                chain.doFilter(request, response);
            }
        } else {
            response.sendRedirect(loginURI);
        }
    }
}
