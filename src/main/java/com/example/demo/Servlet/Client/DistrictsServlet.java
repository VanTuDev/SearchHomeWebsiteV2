package com.example.demo.Servlet.Client;


import com.example.demo.Service.AddressService;
import com.example.demo.Service.PostService;
import com.example.demo.Service.RoomTypeService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.Address;
import com.example.demo.beans.Post;
import com.example.demo.beans.RoomType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@WebServlet(name = "districtServlet",value = "/district")
public class DistrictsServlet extends HttpServlet {
    private final PostService postService = new PostService();
    private final RoomTypeService roomTypeService = new RoomTypeService();
    private final AddressService addressService = new AddressService();
    private static final int POST_PER_PAGE = 5;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoomType> roomTypes = Protector.of(()-> roomTypeService.getPart(10,0)).get(ArrayList::new);
        List<Address> addresses = Protector.of(() -> addressService.getDistrictsByProvince("48")).get(ArrayList::new);
        String districtCode = req.getParameter("id");
        int totalPost = Protector.of(() -> postService.countByQueryDistrictCode(districtCode)).get(0);



        int totalPages = totalPost / POST_PER_PAGE + (totalPost % POST_PER_PAGE != 0 ? 1 : 0);

        String pageParam = Optional.ofNullable(req.getParameter("page")).orElse("1");
        int page = Protector.of(() -> Integer.parseInt(pageParam)).get(1);
        if (page < 1 || page > totalPages) {
            page = 1;
        }

        int offset = (page - 1) * POST_PER_PAGE;

        List<Post> posts = Protector.of(() -> postService.getPostByDistrictCode(
                districtCode, POST_PER_PAGE, offset
        )).get(ArrayList::new);


        req.setAttribute("totalPost", totalPost);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("page", page);
        req.setAttribute("posts", posts);
        req.setAttribute("roomTypes" ,roomTypes);
        req.setAttribute("address" ,addresses);
        req.getRequestDispatcher("/WEB-INF/views/client/districtView.jsp").forward(req, resp);

    }
}
