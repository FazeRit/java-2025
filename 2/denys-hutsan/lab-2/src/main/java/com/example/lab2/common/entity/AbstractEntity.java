package com.example.lab2.common.entity;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class AbstractEntity {
    private UUID  id;
}