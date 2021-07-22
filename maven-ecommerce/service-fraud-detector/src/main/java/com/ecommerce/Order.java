package com.ecommerce;

import java.math.BigDecimal;

public class Order {
    private final String orderId;
    private final BigDecimal amount;

    public Order(String orderId, BigDecimal amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
    boolean isFraud() {
        return amount.compareTo(new BigDecimal("4500")) >= 0;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
