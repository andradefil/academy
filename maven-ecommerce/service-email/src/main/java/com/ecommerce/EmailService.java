package com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class EmailService {
    public static void main(String[] args) {
        var emailService = new EmailService();
        try(var service = new KafkaService<>(EmailService.class.getSimpleName(),
                "ECOMMERCE_SEND_EMAIL", emailService::parse, String.class, Map.of())){
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("------------------------");
        System.out.println("Processing new email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            // igoring because its a simulation
            e.printStackTrace();
        }
        System.out.println("Email sent");
    }
}
