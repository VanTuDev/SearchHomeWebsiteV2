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

@WebServlet(name = "homeView", value = "/homeView")
public class HomeView extends HttpServlet {
    private final PostService postService = new PostService();

    private final RoomTypeService roomTypeService = new RoomTypeService();
    private final AddressService addressService = new AddressService();
    private static final int POST_PER_PAGE = 10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int totalUsers = postService.count();
        int totalPages = totalUsers / POST_PER_PAGE + (totalUsers % POST_PER_PAGE != 0 ? 1 : 0);

        String pageParam = Optional.ofNullable(req.getParameter("page")).orElse("1");
        int page = Integer.parseInt(pageParam);
        if (page < 1 || page > totalPages) {
            page = 1;
        }

        int offset = (page - 1) * POST_PER_PAGE;

        List<Post> posts =  postService.getOrderedPart(
                POST_PER_PAGE, offset, "createAt", "DESC"
        );
        List<RoomType> roomTypes = Protector.of(()-> roomTypeService.getPart(10,0)).get(ArrayList::new);
        List<Address> addresses = Protector.of(() -> addressService.getDistrictsByProvince("48")).get(ArrayList::new);


        req.setAttribute("totalPages", totalPages);
        req.setAttribute("page", page);
        req.setAttribute("posts", posts);
        req.setAttribute("roomTypes" ,roomTypes);
        req.setAttribute("address" ,addresses);
        req.getRequestDispatcher("/WEB-INF/views/client/homeView.jsp").forward(req,resp);
    }
}
