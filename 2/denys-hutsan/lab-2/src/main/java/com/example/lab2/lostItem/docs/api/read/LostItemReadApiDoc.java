package com.example.lab2.lostItem.docs.api.read;

import com.example.lab2.lostItem.entity.LostItemEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface LostItemReadApiDoc {
    @GetMapping("/")
    @Operation(
            summary = "Отримати список втрачених речей",
            description = "Повертає список втрачених речей з пагінацією"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання списку",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LostItemEntity.class)))),
            @ApiResponse(responseCode = "400", description = "Некоректні параметри", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    ResponseEntity<List<LostItemEntity>> getLostItems(
            @Parameter(description = "Номер сторінки (починаючи з 1)", example = "1")
            @RequestParam(defaultValue = "1") int page,

            @Parameter(description = "Кількість елементів на сторінку", example = "10")
            @RequestParam(defaultValue = "10") int itemsPerPage
    );

    @GetMapping("/{id}")
    @Operation(
            summary = "Отримати втрачену річ за ID",
            description = "Повертає одну втрачену річ за її унікальним ідентифікатором"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання об’єкта",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = LostItemEntity.class))),
            @ApiResponse(responseCode = "404", description = "Річ не знайдена",
                    content = @Content(schema = @Schema(example = """
                            {
                              "timestamp": "2024-10-01T12:00:00",
                              "status": 404,
                              "error": "Not Found",
                              "message": "Lost item with id {provided id} not found",
                              "path": "/api/lost-items/{provided id}"
                            }"""))),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    ResponseEntity<LostItemEntity> getLostItemById(
            @Parameter(description = "UUID втраченого предмета", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
            @PathVariable UUID id
    );

    @GetMapping("/search")
    @Operation(
            summary = "Пошук втрачених речей",
            description = "Дозволяє шукати речі за ім'ям та тегами з пагінацією"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успішне отримання результатів пошуку",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LostItemEntity.class)))),
            @ApiResponse(responseCode = "400", description = "Некоректні параметри", content = @Content),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера", content = @Content)
    })
    ResponseEntity<List<LostItemEntity>> searchLostItems(
            @Parameter(description = "Номер сторінки (починаючи з 1)", example = "1")
            @RequestParam(defaultValue = "1") int page,

            @Parameter(description = "Кількість елементів на сторінку", example = "10")
            @RequestParam(defaultValue = "10") int itemsPerPage,

            @Parameter(description = "Фільтр за назвою речі", example = "Wallet")
            @RequestParam(required = false) String name,

            @Parameter(description = "Фільтр за тегами", example = "[\"electronics\", \"black\"]")
            @RequestParam(required = false) List<String> tags
    );
}