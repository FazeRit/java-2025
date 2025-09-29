package com.example.lab2.lostItem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "DTO для створення нового запису про втрачену річ")
public class LostItemCreateDto {

    @Schema(description = "Назва речі", example = "Black Wallet")
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Schema(description = "Опис речі", example = "Leather wallet with several cards inside")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @Schema(description = "Список тегів для швидкого пошуку (макс 10)",
            example = "[\"wallet\", \"black\", \"leather\"]")
    @Size(max = 10, message = "Tags list cannot exceed 10 items")
    private List<String> tags;

    @Schema(description = "Місце, де знайдено або втрачено річ", example = "Kyiv Central Station")
    @NotBlank(message = "Location cannot be empty")
    @Size(min = 2, max = 200, message = "Location must be between 2 and 200 characters")
    private String location;

    @Schema(description = "Контактний номер телефону у міжнародному форматі",
            example = "+380931234567")
    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$",
            message = "Phone number must be a valid international format (e.g., +1234567890)")
    private String phoneNumber;
}