package com.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderDomain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(producerProperties());

        for (var i=0;i<100;i++) {
            var key = UUID.randomUUID().toString();
            var value = key + ",3125678321,123";

            // FRAUD DETECTION SERVICE
            var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", key, value);
            producer.send(record, getCallback()).get();
            // EMAIL SERVICE
            var email = "Thank you for your order! We are processing it!";
            var emailRecord = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", key, email);
            producer.send(emailRecord, getCallback()).get();
        }

    }

    private static Callback getCallback() {
        return (data, ex) -> {
            if(ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("success:" + data.topic() + "/offset:" + data.offset() + "/" + data.timestamp());
        };
    }

    private static Properties producerProperties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
