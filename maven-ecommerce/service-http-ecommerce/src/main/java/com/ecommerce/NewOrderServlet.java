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
    private KafkaDispatcher dispatcher;

    @Override
    public void init() throws ServletException {
        super.init();
        this.dispatcher = new KafkaDispatcher<>();
    }

    @Override
    public void destroy() {
        super.destroy();
        dispatcher.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        var email = req.getParameter("email");
        var amount = new BigDecimal(req.getParameter("amount"));
        var orderId = UUID.randomUUID().toString();
        var order = new Order(email, orderId, amount);

        try {
            dispatcher.send("ECOMMERCE_NEW_ORDER", email, order);
            System.out.println("The first message was sent.");

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
