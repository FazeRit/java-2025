package com.example.lab2.lostItem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

public record LostItemUpdateDto(
        String name,
        String description,
        List<String> tags,
        String location,
        String phoneNumber
) {}