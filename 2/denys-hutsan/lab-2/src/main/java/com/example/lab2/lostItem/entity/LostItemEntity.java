package com.example.lab2.lostItem.entity;

import com.example.lab2.common.entity.AbstractEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "Сутність втраченого предмета")
public class LostItemEntity extends AbstractEntity {

    @Schema(description = "Назва речі", example = "Black Wallet")
    private String name;

    @Schema(description = "Опис речі", example = "Leather wallet with several cards inside")
    private String description;

    @Schema(description = "Теги для пошуку", example = "[\"wallet\", \"black\", \"leather\"]")
    private List<String> tags;

    @Schema(description = "Місце, де знайдено або втрачено річ", example = "Kyiv Central Station")
    private String location;

    @Schema(description = "Контактний номер телефону", example = "+380931234567")
    private String phoneNumber;

    @Schema(description = "Дата створення запису", example = "2025-09-29T12:34:56")
    private LocalDateTime createdAt;

    @Schema(description = "Дата останнього оновлення запису", example = "2025-09-29T12:40:00")
    private LocalDateTime updatedAt;

    public LostItemEntity(String name, String description, List<String> tags, String location, String phoneNumber) {
        this.name = name;
        this.description = description;
        this.tags = tags != null ? new ArrayList<>(tags) : new ArrayList<>();
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
