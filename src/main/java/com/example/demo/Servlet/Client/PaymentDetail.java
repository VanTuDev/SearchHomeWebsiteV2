package com.example.demo.Servlet.Client;

import com.example.demo.Service.PayingService;
import com.example.demo.Utils.Protector;

import com.example.demo.beans.Paying;
import com.example.demo.beans.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

@WebServlet("/paymentHistory")
public class PaymentDetail extends HttpServlet {
    private final PayingService payingService = new PayingService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        List<Paying> payings = payingService.getBulkById(user.getId());
        req.setAttribute("paymentDetails",payings);
        req.getRequestDispatcher("/WEB-INF/views/client/paymentHistory.jsp").forward(req, resp);
    }

}
