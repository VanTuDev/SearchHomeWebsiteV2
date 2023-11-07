package com.example.demo.Servlet.General;
import com.example.demo.Service.AddressService;
import com.example.demo.beans.Address;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/loadAddressName")
public class LoadAddress extends HttpServlet{
    private final AddressService addressService = new AddressService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address address = new Address();

        address.setWardsCode(Integer.valueOf(req.getParameter("wardCode")));
        address.setDistrictCode(Integer.valueOf(req.getParameter("districtCode")));
        address.setProvinceCode(Integer.valueOf(req.getParameter("provinceCode")));
        String wardName =  addressService.loadProvinceCode(address.getProvinceCode());
        String districtName = addressService.loadDistrictCode(address.getDistrictCode());
        String provinceName = addressService.loadWardCode(address.getWardsCode());
        System.out.println(wardName+" " + districtName + provinceName);

        sendJsonResponse(resp, wardName,districtName,provinceName);

    }
    private void sendJsonResponse(HttpServletResponse response, Object... data) throws IOException {
        Gson gson = new Gson();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(data));
    }
}
