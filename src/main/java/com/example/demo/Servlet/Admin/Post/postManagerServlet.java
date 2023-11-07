package com.example.demo.Servlet.Admin.Post;



import com.example.demo.Service.RoomTypeService;
import com.example.demo.beans.RoomType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "postManagerServlet", value = "/admin/postManager")
public class postManagerServlet extends HttpServlet {
    private final RoomTypeService roomTypeService = new RoomTypeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoomType> roomTypesFromServer = roomTypeService.getAll();
        req.setAttribute("roomType",roomTypesFromServer);
        req.getRequestDispatcher("/WEB-INF/views/admin/post/postManagerView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/admin/post/postCreateView.jsp").forward(req, resp);
    }

}
