package com.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface ConsumerFunction<T> {
    // throws Exception is usually a veeeery bad practice.
    // so libraries will create their own exception and tell
    // end-users (end-developers) to warp it
    void parse(ConsumerRecord<String, T> record) throws CommonKafkaException;
}
