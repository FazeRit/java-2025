package com.example.lab2.common.entity;

import java.util.UUID;

public class AbstractEntity {
    private UUID  id;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}