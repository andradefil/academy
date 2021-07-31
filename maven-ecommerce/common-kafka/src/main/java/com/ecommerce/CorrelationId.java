package com.ecommerce;

import java.util.UUID;

public class CorrelationId {
    private final String id;

    public CorrelationId() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "CorrelationId{" +
                "id='" + id + '\'' +
                '}';
    }
}
