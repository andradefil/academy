package com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.SQLException;
import java.util.Map;

public class CreateUserService {

    public static void main(String[] args) throws SQLException {
        var createUserService = new CreateUserService();
        try (var service = new KafkaService<>(
                CreateUserService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                createUserService::parse,
                Order.class,
                Map.of()
        )) {
            service.run();
        }
    }

    private final Users users;

    CreateUserService() throws SQLException {
        this.users = new Users();
    }

    private void parse(ConsumerRecord<String, Order> record) throws CommonKafkaException {
        System.out.println("==================================");
        System.out.println("Processing new order, checking for new user");
        var order = record.value();
        try {
            if (users.isNewUser(order.getEmail())){
                users.insertNewUser(order.getEmail());
            }
        }catch (SQLException e) {
            throw new CommonKafkaException(e);
        }
    }
}
