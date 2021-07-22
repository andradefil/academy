package com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FraudDetectorService {
    private final KafkaDispatcher<Order> dispatcher = new KafkaDispatcher<>();

    public static void main(String[] args) {
        var fraudDetectorService = new FraudDetectorService();
        try (var service = new KafkaService<>(FraudDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                fraudDetectorService::parse,
                Order.class,
                Map.of()
        )) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
        System.out.println("------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(record.key());
        var order = record.value();
        System.out.println(order);
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            // igoring because its a simulation
            e.printStackTrace();
        }
        if(order.isFraud()) {
            System.out.println("Order is a Fraud");
            dispatcher.send("ECOMMERCE_ORDER_REJECTED", order.getOrderId(), order);
        } else {
            System.out.println("Order was accepted");
            dispatcher.send("ECOMMERCE_ORDER_APPROVED", order.getOrderId(), order);
        }
    }


}
