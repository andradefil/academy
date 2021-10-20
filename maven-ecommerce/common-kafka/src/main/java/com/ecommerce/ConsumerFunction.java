package com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerFunction<T> {
    void parse(ConsumerRecord<String, T> record);
}
