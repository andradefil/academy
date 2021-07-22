package com.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderDomain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (var dispatcher = new KafkaDispatcher<>()) {
            var email = UUID.randomUUID() + "@email.com";
            for (var i=0;i<10;i++) {
                var orderId = UUID.randomUUID().toString();
                var amount = BigDecimal.valueOf(Math.random() * 5000 + 1);
                var order = new Order(email, orderId, amount);

                var emailContent = "Thank you for your order! We are processing it!";
                dispatcher.send("ECOMMERCE_NEW_ORDER", email, order);
                dispatcher.send("ECOMMERCE_SEND_EMAIL", orderId, emailContent);
            }
        }
    }
}
