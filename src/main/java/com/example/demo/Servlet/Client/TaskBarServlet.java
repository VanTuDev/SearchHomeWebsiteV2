package com.example.demo.Servlet.Client;



import com.example.demo.Service.AddressService;
import com.example.demo.Service.RoomTypeService;
import com.example.demo.Utils.Protector;
import com.example.demo.beans.Address;
import com.example.demo.beans.RoomType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "postManagerServlet", value = "/admin/postManager")
public class TaskBarServlet extends HttpServlet {
    private final RoomTypeService roomTypeService = new RoomTypeService();
    private final AddressService addressService = new AddressService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoomType> roomTypesFromServer = roomTypeService.getAll();
        List<Address> addresses = Protector.of(() -> addressService.getDistrictsByProvince("48")).get(ArrayList::new);

        req.setAttribute("roomType",roomTypesFromServer);
        req.setAttribute("address",addresses);
        req.getRequestDispatcher("/WEB-INF/views/client/taskbar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/client/taskbar.jsp").forward(req, resp);
    }

}
