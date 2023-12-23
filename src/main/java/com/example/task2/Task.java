package com.example.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class Task<T> implements StampedTask {
    private String id;
    private Map<String, String> headers;

    public abstract void apply(T arg);

    public void freeze() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    protected void setHeader(String header, String headerValue) {
        if (headers == null) {
            headers = new HashMap<>();
        }
        headers.put(header, headerValue);
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    @Override
    public void stamp(String groupId) {
        setHeader("groupId", groupId);
    }
}