package com.SearchHome_V2.Servlet.Admin.User;
import com.SearchHome_V2.Service.AddressService;
import com.SearchHome_V2.Service.UserService;


import com.SearchHome_V2.Utils.ImageUtils;
import com.SearchHome_V2.Utils.Protector;
import com.SearchHome_V2.Utils.Validator;
import com.SearchHome_V2.beans.Address;
import com.SearchHome_V2.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@WebServlet(name = "createUser",value = "/admin/userManager/create")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class createUserServlet extends HttpServlet {
    private final UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/WEB-INF/views/admin/userCreateView.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("username"));
        user.setPassword(req.getParameter("password"));
        user.setFullName(req.getParameter("fullName"));
        user.setGender(Integer.parseInt(req.getParameter("gender")));
        user.setPhoneNumber(req.getParameter("phoneNumber"));
        user.setEmail(req.getParameter("email"));
        user.setCccd(req.getParameter("cccd"));
        user.setRole(req.getParameter("role"));
        System.out.println(user);


        Optional<User> userByUsername = userService.getByUsername(user.getUsername());
        Optional<User> userByEmail =  userService.getByEmail(user.getEmail());
        Optional<User> userByPhoneNumber =  userService.getByPhoneNumber(user.getPhoneNumber());
        Map<String, List<String>> violations = new HashMap<>();
        //-------------Validation time !--------------//
        violations.put("usernameViolations", Validator.of(user.getUsername())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(25)
                .isNotExistent(userByUsername.isPresent(), "Tên đăng nhập")
                .toList());
        violations.put("passwordViolations", Validator.of(user.getPassword())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(32)
                .toList());
        violations.put("fullNameViolations", Validator.of(user.getFullName())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .toList());
        violations.put("emailViolations", Validator.of(user.getEmail())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .hasPattern("^[^@]+@[^@]+\\.[^@]+$", "email")
                .isNotExistent(userByEmail.isPresent(), "Email")
                .toList());
        violations.put("phoneNumberViolations", Validator.of(user.getPhoneNumber())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .hasPattern("^\\d{10,11}$", "số điện thoại")
                .isNotExistent(userByPhoneNumber.isPresent(), "Số điện thoại")
                .toList());
        violations.put("genderViolations", Validator.of(user.getGender())
                .isNotNull()
                .toList());
        violations.put("cccdViolations", Validator.of(user.getCccd())
                .isNotBlankAtBothEnds()
                .isNotNull()
                .toList());
        violations.put("roleViolations", Validator.of(user.getRole())
                .isNotNull()
                .toList());

        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Thêm thành công!";
        String errorMessage = "Thêm thất bại!";
        if (sumOfViolations == 0) {
            ImageUtils.upload(req).ifPresent(user::setImageName);
            Protector.of(() -> userService.insert(user))
                    .done(r -> req.setAttribute("successMessage", successMessage))
                    .fail(e -> {
                        req.setAttribute("user", user);
                        req.setAttribute("errorMessage", errorMessage);
                    });
        } else {
            req.setAttribute("user", user);
            req.setAttribute("violations", violations);
        }

        req.getRequestDispatcher("/WEB-INF/views/admin/userCreateView.jsp").forward(req,resp);
    }
}