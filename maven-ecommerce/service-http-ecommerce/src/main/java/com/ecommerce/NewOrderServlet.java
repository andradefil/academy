package com.ecommerce;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try (var dispatcher = new KafkaDispatcher<>()) {
            var email = req.getParameter("email");
            var amount = new BigDecimal(req.getParameter("amount"));
            var orderId = UUID.randomUUID().toString();
            var order = new Order(email, orderId, amount);

            try {
                dispatcher.send("ECOMMERCE_NEW_ORDER", email, order);
                var emailContent = "Thank you for your order! We are processing it!";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", orderId, emailContent);

                System.out.println("New order sent successfully.");
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("New order sent!");
            } catch (ExecutionException | InterruptedException | IOException e) {
                throw new ServletException(e);
            }
        }
    }
}
