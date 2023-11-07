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
import java.time.LocalDateTime;
import java.util.*;

@WebServlet("/updatePostFromUser")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
        maxFileSize = 1024 * 1024 * 5, // 5 MB
        maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class UpdatePostServlet extends HttpServlet {
    private final RoomTypeService roomTypeService = new RoomTypeService();
    private final UserService userService = new UserService();
    private final PostService postService = new PostService();
    private final ImageService imageService = new ImageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoomType> roomTypes = Protector.of(()-> roomTypeService.getPart(10,0)).get(ArrayList::new);
        long id = Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L);
        Optional<Post> postFromServer =  Protector.of(() -> postService.getById(id)).get(Optional::empty);
        List<Image> imageFromServer = Protector.of(() -> imageService.getByImageByPostId(postFromServer.get().getId())).get(ArrayList::new);
        if (postFromServer.isPresent()) {
            req.setAttribute("post", postFromServer.get());
            req.setAttribute("roomTypes" ,roomTypes);
            req.setAttribute("image",imageFromServer);
            postFromServer.ifPresent(roomType -> req.setAttribute("roomTypeId", roomType.getId()));
            req.getRequestDispatcher("/WEB-INF/views/client/updatePostView.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Post();
        post.setId(Protector.of(() -> Long.parseLong(req.getParameter("id"))).get(0L));
        post.setTitle(req.getParameter("title"));
        post.setDescriptions(req.getParameter("descriptions").trim().isEmpty()
                ? null : req.getParameter("descriptions"));
        post.setPrice(Protector.of(() -> Double.parseDouble(req.getParameter("price"))).get(0d));
        post.setImageName(req.getParameter("imageName").trim().isEmpty()
                ? null : req.getParameter("imageName"));
        post.setArea(req.getParameter("area"));
        post.setAddressDetail(req.getParameter("addressDetail"));
        post.setUserId( Protector.of(() -> Long.parseLong(req.getParameter("userId"))).get(0L));
        post.setStartAt(LocalDateTime.now());
        post.setEndAt(
                LocalDateTime.now()
        );
        String deleteImage = req.getParameter("deleteImage");
        String deleteImageDetail = req.getParameter("deleteImageDetail");
        Address address = new Address(
                48 ,
                "Đà Nẵng" ,
                Protector.of(() -> Integer.parseInt(req.getParameter("districtCode"))).get(0)
                ,
                "",
                Protector.of(() -> Integer.parseInt(req.getParameter("wardsCode"))).get(0)
                ,
                ""
        );
        post.setProvinceCode(address.getProvinceCode());
        post.setDistrictCode(address.getDistrictCode());
        post.setWardCode(address.getWardsCode());
        post.setCreateAt(LocalDateTime.now());
        long roomTypeId = Protector.of(() -> Long.parseLong(req.getParameter("roomTypeId"))).get(0L);
        post.setRoomTypeId(roomTypeId);
        Optional<User> userFromServer = userService.getUserIdVer2(post.getUserId());
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
        violations.put("provinceViolations", Optional.of(post.getProvinceCode()).filter(id -> id == 0)
                .map(id -> Collections.singletonList("Phải chọn thành phố"))
                .orElseGet(Collections::emptyList));
        violations.put("districtViolations", Optional.of(post.getDistrictCode()).filter(id -> id == 0)
                .map(id -> Collections.singletonList("Phải chọn Quận"))
                .orElseGet(Collections::emptyList));
        violations.put("wardViolations", Optional.of(post.getWardCode()).filter(id -> id == 0)
                .map(id -> Collections.singletonList("Phải chọn Phường"))
                .orElseGet(Collections::emptyList));

        violations.put("addressDetailViolations",Validator.of(post.getAddressDetail())
                .isAtMostOfLength(50)
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("createAtViolations",Validator.of(post.getCreateAt())
                .isNotNullAndEmpty()
                .toList()
        );
        violations.put("roomTypesViolations", Optional.of(roomTypeId).filter(id -> id == 0)
                .map(id -> Collections.singletonList("Phải chọn thể loại phòng"))
                .orElseGet(Collections::emptyList));
        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Chỉnh sửa thành công!";
        String errorMessage = "Chỉnh sửa thất bại!";
        System.out.println(violations);
        System.out.println(post);


        if (sumOfViolations == 0 ) {
            if (post.getImageName() != null){
                String currentImageName = post.getImageName();
                if (deleteImage != null) {
                    ImageUtils.delete(currentImageName);
                    post.setImageName(null);
                }
                ImageUtils.upload(req).ifPresent(imageName -> {
                    ImageUtils.delete(currentImageName);
                    post.setImageName(imageName);
                });
            }else{
                ImageUtils.upload(req).ifPresent(post::setImageName);
            }
            Protector.of(() -> {
                        postService.update(post);
                    })
                    .done(r -> req.setAttribute("successMessage", successMessage))
                    .fail(e -> req.setAttribute("errorMessage", errorMessage));
        } else {
            req.setAttribute("violations", violations);
        }

        List<RoomType> roomTypesFromServer = Protector.of(roomTypeService::getAll).get(ArrayList::new);
        List<Image> images = Protector.of(() -> imageService.getByImageByPostId(post.getId())).get(ArrayList::new);
        req.setAttribute("image",images);
        req.setAttribute("roomTypes",roomTypesFromServer);
        req.setAttribute("post", post);
        req.setAttribute("roomTypeId", roomTypeId);
        req.getRequestDispatcher("/WEB-INF/views/client/updatePostView.jsp").forward(req,resp);

    }
}
