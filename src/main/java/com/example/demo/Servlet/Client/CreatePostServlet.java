package com.example.demo.Servlet.Client;


import com.example.demo.Service.ImageService;
import com.example.demo.Service.PostService;
import com.example.demo.Service.RoomTypeService;
import com.example.demo.Service.UserService;
import com.example.demo.Utils.ImageUtils;
import com.example.demo.Utils.Protector;
import com.example.demo.Utils.Validator;
import com.example.demo.beans.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@WebServlet(name = "createPostFromUser",value = "/createPostFromUser")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class CreatePostServlet extends HttpServlet {
    private final RoomTypeService roomTypeService = new RoomTypeService();
    private final UserService userService = new UserService();
    private final PostService postService = new PostService();
    private final ImageService imageService = new ImageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoomType> roomTypes = Protector.of(()-> roomTypeService.getPart(10,0)).get(ArrayList::new);
        req.setAttribute("roomTypes" ,roomTypes);
        req.getRequestDispatcher("/WEB-INF/views/client/createPost.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Post();


        List<Image> images = new ArrayList<>();
        Address address = new Address(
                48 ,
                "Đà Nẵng" ,
                Integer.parseInt(req.getParameter("districtCode")) ,
                "",
                Integer.parseInt(req.getParameter("wardsCode")) ,
                ""
        );
        System.out.println(address);

        post.setTitle(req.getParameter("title").trim().isEmpty()
                ? null : req.getParameter("title"));
        post.setDescriptions(req.getParameter("descriptions").trim().isEmpty()
                ? null : req.getParameter("descriptions"));

        post.setPrice(Protector.of(() -> Double.parseDouble(req.getParameter("price"))).get(0d));
        post.setArea(req.getParameter("area").trim().isEmpty()
                ? null : req.getParameter("area"));
        post.setAddressDetail(req.getParameter("addressDetail").trim().isEmpty()
                ? null : req.getParameter("addressDetail"));
        post.setUserId( Protector.of(() -> Long.parseLong(req.getParameter("userId"))).get(0L));
        post.setRoomTypeId( Protector.of(() -> Long.parseLong(req.getParameter("roomTypeId"))).get(0L));
        post.setStartAt(LocalDateTime.now());
        post.setEndAt(
                LocalDateTime.now()
        );

        post.setProvinceCode(address.getProvinceCode());
        post.setDistrictCode(address.getDistrictCode());
        post.setWardCode(address.getWardsCode());
        post.setCreateAt(LocalDateTime.now());
        System.out.println(post);
        Optional<User> userFromServer = userService.getUserIdVer2(post.getUserId());


        List<RoomType> roomTypesFromServer = roomTypeService.getAll();
        Map<String, List<String>> violations = new HashMap<>();

        violations.put("titleViolations", Validator.of(post.getTitle())
                .isAtMostOfLength(100)
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("descriptionViolations",Validator.of(post.getDescriptions())
                .isAtMostOfLength(350)
                .toList()
        );
        violations.put("priceViolations",Validator.of(post.getPrice())
                .isLargerThan(0, "Giá gốc")
                .isNotNullAndEmpty()
                .toList()
        );
        userFromServer.filter(user -> user.getValid() == 101)
                .ifPresent(user -> violations.put("usersViolation", Collections.singletonList("Phải đợi xác thực từ hệ thống mới được đăng bài")));
        violations.put("areaViolations",Validator.of(post.getArea())
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("addressDetail",Validator.of(post.getAddressDetail())
                .isAtMostOfLength(50)
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("createAtViolations",Validator.of(post.getCreateAt())
                .isNotNullAndEmpty()
                .toList()
        );
        System.out.println(violations);
        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Thêm thành công!";
        String errorMessage = "Thêm thất bại!";
        System.out.println(violations);
        List<String> uploadedFileNames = ImageUtils.uploadMultiple(req);

        if (sumOfViolations == 0 && !uploadedFileNames.isEmpty()) {
            ImageUtils.upload(req).ifPresent(post::setImageName);
            long id = postService.insert(post);
            System.out.println(id);
            if (id > 0L){
                for (String fileName : uploadedFileNames) {
                    Image image = new Image(1L, fileName, id);
                    imageService.insert(image);
                }
                req.setAttribute("roomTypes",roomTypesFromServer);
                req.setAttribute("successMessage", successMessage);
            }else {
                req.setAttribute("roomTypes",roomTypesFromServer);
                req.setAttribute("errorMessage", errorMessage);
            }

        } else {
            req.setAttribute("roomTypes",roomTypesFromServer);
            req.setAttribute("post", post);
            req.setAttribute("violations", violations);
        }

        req.setAttribute("roomTypes",roomTypesFromServer);
        req.getRequestDispatcher("/WEB-INF/views/client/createPost.jsp").forward(req,resp);

    }
}
