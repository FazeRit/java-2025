package com.example.lab2.lostItem.entity;

import com.example.lab2.common.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class LostItemEntity extends AbstractEntity {
    private String name;

    private String description;

    private List<String> tags;

    private String location;

    private String phoneNumber;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public LostItemEntity(String name, String description, List<String> tags, String location,
                          String phoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.tags = tags != null ? List.copyOf(tags) : null;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public LostItemEntity(String name, String description, List<String> tags, String location, String phoneNumber) {
        this.name = name;
        this.description = description;
        this.tags = tags != null ? List.copyOf(tags) : null;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
