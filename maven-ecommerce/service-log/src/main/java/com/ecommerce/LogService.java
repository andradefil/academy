package com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.regex.Pattern;

public class LogService {
    public static void main(String[] args) {
        var logService = new LogService();
        try(var service = new KafkaService<>(
                LogService.class.getSimpleName(),
                Pattern.compile("ECOMMERCE.*"),
                logService::parse,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName())
        )) {
            service.run();
        };
    }

    private <T> void parse(ConsumerRecord<String,T> record) {
        System.out.println("------------------------");
        System.out.println("Log");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.topic());
        System.out.println(record.partition());
        System.out.println(record.offset());
    }
}
