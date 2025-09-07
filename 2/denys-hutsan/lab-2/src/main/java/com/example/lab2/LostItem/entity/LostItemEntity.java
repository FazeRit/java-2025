package com.example.lab2.LostItem.entity;

import com.example.lab2.common.entity.AbstractEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
