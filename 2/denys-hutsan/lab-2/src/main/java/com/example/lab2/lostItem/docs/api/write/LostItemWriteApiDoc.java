package com.example.lab2.lostItem.docs.api.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface LostItemWriteApiDoc {
    @PostMapping("/create")
    @Operation(
            summary = "Створити новий запис про втрачену річ",
            description = "Додає новий запис у базу даних та повертає створену сутність"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успішно створено",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LostItemEntity.class))),
            @ApiResponse(responseCode = "400", description = "Некоректні дані у запиті",
                    content = @Content(schema = @Schema(example = "{ \"status\": 400, \"error\": \"Bad Request\", \"message\": \"Validation failed\" }"))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    ResponseEntity<LostItemEntity> createLostItem(
            @Parameter(description = "DTO з даними для створення нового запису", required = true)
            @Valid @RequestBody LostItemCreateDto lostItem
    );

    @PutMapping("/{id}")
    @Operation(
            summary = "Оновити інформацію про втрачену річ",
            description = "Оновлює дані існуючої речі за її унікальним ідентифікатором"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне оновлення",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LostItemEntity.class))),
            @ApiResponse(responseCode = "400", description = "Некоректні дані у запиті",
                    content = @Content(schema = @Schema(example = """
                            {
                              "timestamp": "2024-10-01T12:00:00",
                              "status": 400,
                              "error": "Bad Request",
                              "message": "400 Validation failed",
                              "path": "/api/lost-items/{provided id}"
                            }"""))),
            @ApiResponse(responseCode = "404", description = "Річ не знайдена",
                    content = @Content(schema = @Schema(example = """
                            {
                              "timestamp": "2024-10-01T12:00:00",
                              "status": 404,
                              "error": "Not Found",
                              "message": "404 NOT_FOUND",
                              "path": "/api/lost-items/{provided id}"
                            }"""))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    ResponseEntity<LostItemEntity> updateLostItem(
            @Parameter(description = "UUID речі для оновлення", required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID id,

            @Parameter(description = "DTO з новими даними для речі", required = true)
            @Valid @RequestBody LostItemUpdateDto lostItem
    );

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Видалити втрачену річ",
            description = "Видаляє запис про річ за її унікальним ідентифікатором"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Успішно видалено (без тіла відповіді)"),
            @ApiResponse(responseCode = "404", description = "Річ не знайдена",
                    content = @Content(schema = @Schema(example = """
                            {
                              "timestamp": "2024-10-01T12:00:00",
                              "status": 404,
                              "error": "Not Found",
                              "message": "404 NOT_FOUND",
                              "path": "/api/lost-items/{provided id}"
                            }"""))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    ResponseEntity<Void> deleteLostItem(
            @Parameter(description = "UUID речі для видалення", required = true,
                    example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID id
    );
}