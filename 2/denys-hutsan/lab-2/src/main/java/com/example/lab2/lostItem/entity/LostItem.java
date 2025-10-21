package com.example.lab2.lostItem.entity;

import java.util.List;

public record LostItem(
        Integer id,
        String name,
        String description,
        String phoneNumber,
        java.time.LocalDateTime createdAt,
        java.time.LocalDateTime updatedAt,
        String location,
        List<String> tags
) {}
