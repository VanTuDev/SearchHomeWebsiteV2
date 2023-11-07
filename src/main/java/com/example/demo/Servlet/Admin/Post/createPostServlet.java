package com.example.demo.Servlet.Admin.Post;


import com.example.demo.Service.AddressService;
import com.example.demo.Service.PostService;
import com.example.demo.Service.RoomTypeService;
import com.example.demo.Utils.ImageUtils;
import com.example.demo.Utils.Protector;
import com.example.demo.Utils.Validator;
import com.example.demo.beans.Address;
import com.example.demo.beans.Post;
import com.example.demo.beans.RoomType;
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
import java.util.logging.Level;
import java.util.logging.Logger;





@WebServlet(name = "createPost",value = "/admin/postManager/create")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class createPostServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Post.class.getName());

    private final AddressService addressService = new AddressService();
    private final RoomTypeService roomTypeService = new RoomTypeService();
    private final PostService postService = new PostService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Post();
        Address address = new Address(
                48 ,
                "Đà Nẵng" ,
                Integer.parseInt(req.getParameter("districtCode")) ,
                req.getParameter("districtName") ,
                Integer.parseInt(req.getParameter("wardsCode")) ,
                req.getParameter("wardsName")
        );
        post.setTitle(req.getParameter("title"));
        post.setDescriptions(req.getParameter("descriptions"));
        post.setPrice(Protector.of(() -> Double.parseDouble(req.getParameter("price"))).get(0d));
        post.setArea(req.getParameter("area"));
        post.setAddressDetail(req.getParameter("addressDetail"));
        post.setUserId(
                Long.parseLong(req.getParameter("userId"))
        );
        post.setRoomTypeId(
                Long.parseLong(req.getParameter("roomType"))
        );
        post.setStartAt(
                LocalDateTime.now()
        );
        try {
            post.setEndAt(
                    LocalDateTime.of(
                            LocalDate.parse(
                                    req.getParameter("endAt"),
                                    DateTimeFormatter.ofPattern("dd/MM/yyyy")
                            ),
                            LocalTime.MIDNIGHT
                    )
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred", e);
        }

        post.setProvinceCode(address.getProvinceCode());
        post.setDistrictCode(address.getDistrictCode());
        post.setWardCode(address.getWardsCode());
        post.setCreateAt(LocalDateTime.now());

        Map<String, List<String>> violations = new HashMap<>();
        violations.put("titleViolations", Validator.of(post.getTitle())
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isAtMostOfLength(100)
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("descriptionViolations",Validator.of(post.getDescriptions())
                .isAtMostOfLength(2000)
                .toList()
        );
        violations.put("priceViolations",Validator.of(post.getPrice())
                .isNotNull()
                .isLargerThan(0, "Giá thuê hoặc mua")
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("areaViolations",Validator.of(post.getArea())
                .isNotNull()
                .isLargerThan(0, "Giá thuê hoặc mua")
                .isNotNullAndEmpty()
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("addressDetail",Validator.of(post.getAddressDetail())
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("roomTypeViolations", Optional.of(post.getRoomTypeId()).filter(id -> id == 0)
                .map(id -> Collections.singletonList("Phải chọn danh mục cho bài đăng"))
                .orElseGet(Collections::emptyList));

        violations.put("createAtViolations",Validator.of(post.getCreateAt())
                .isNotNullAndEmpty()
                .toList()
        );
        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        List<RoomType> roomTypesFromServer = roomTypeService.getAll();
        String successMessage = "Thêm thành công!";
        String errorMessage = "Thêm thất bại!";
        if (sumOfViolations == 0) {
            ImageUtils.upload(req).ifPresent(post::setImageName);
            Protector.of(() -> postService.insert(post))
                    .done(r ->{
                        req.setAttribute("roomType",roomTypesFromServer);
                        req.setAttribute("successMessage", successMessage);
                        req.setAttribute("operationResult", "success");

                    } )
                    .fail(e -> {
                        req.setAttribute("roomType",post.getRoomType());
                        req.setAttribute("post", post);
                        req.setAttribute("errorMessage", errorMessage);
                        req.setAttribute("operationResult", "failure");
                    });
        } else {
            req.setAttribute("roomType",roomTypesFromServer);
            req.setAttribute("post", post);
            req.setAttribute("violations", violations);
        }

        req.setAttribute("roomType",roomTypesFromServer);
        req.getRequestDispatcher("/WEB-INF/views/admin/post/postManagerView.jsp").forward(req,resp);

    }
}
