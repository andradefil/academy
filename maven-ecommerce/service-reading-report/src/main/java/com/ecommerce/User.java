package com.ecommerce;

public class User {
    private final String uuid;

    @Override
    public String toString() {
        return super.toString();
    }

    public User(String uuid) {
        this.uuid = uuid;
    }

    public String getReportPath() {
        return "target/" + uuid + "-report.txt";
    }

    public String getUuid() {
        return uuid;
    }
}
