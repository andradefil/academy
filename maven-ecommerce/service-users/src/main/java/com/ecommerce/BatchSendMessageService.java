package com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BatchSendMessageService {
    public static void main(String[] args) throws SQLException {
        var batchSendMessageService = new BatchSendMessageService();
        try (var service = new KafkaService<>(
                CreateUserService.class.getSimpleName(),
                "ECOMMERCE_SEND_MESSAGE_FOR_EVERY_USER",
                batchSendMessageService::parse,
                String.class,
                Map.of()
        )) {
            service.run();
        }
    }

    private final Users users;
    private final KafkaDispatcher<User> dispatcher = new KafkaDispatcher<User>();

    BatchSendMessageService() throws SQLException {
        this.users = new Users();
    }

    private void parse(ConsumerRecord<String, String> record) throws CommonKafkaException {
        System.out.println("==================================");
        System.out.println("Processing a new batch for every user");
        var topic = record.value();
        try {
            for (User user : users.all()) {
                try {
                    dispatcher.send(topic, user.getUuid(), user);
                    System.out.println("Processing for user " + user);
                } catch (ExecutionException | InterruptedException e) {
                    // i am just skipping the user
                    e.printStackTrace();
                }
            }
        }catch (SQLException e) {
            throw new CommonKafkaException(e);
        }
    }
}
