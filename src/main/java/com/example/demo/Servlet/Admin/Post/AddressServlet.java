package com.example.demo.Servlet.Admin.Post;


import com.example.demo.Service.AddressService;
import com.example.demo.beans.Address;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "addressAPI", value = "/addressServlet")
public class AddressServlet extends HttpServlet {
    private final AddressService addressService = new AddressService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("getProvinces".equals(action)) {
            List<Address> provinces = addressService.getAllProvinces();
            sendJsonResponse(resp, provinces);
        } else if ("getDistricts".equals(action)) {
            String provinceCode = req.getParameter("provinceCode");
            List<Address> districts = addressService.getDistrictsByProvince(provinceCode);
            sendJsonResponse(resp, districts);
        } else if ("getWards".equals(action)) {
            String districtCode = req.getParameter("districtCode");
            List<Address> wards = addressService.getWardsByDistrict(districtCode);
            sendJsonResponse(resp, wards);
        }

    }
    private void sendJsonResponse(HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(data));
    }
}
