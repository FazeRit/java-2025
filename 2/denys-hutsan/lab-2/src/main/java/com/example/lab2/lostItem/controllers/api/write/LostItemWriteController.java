package com.example.lab2.lostItem.controllers.api.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.UUID;

// TODO: make exception handler
@RestController
@RequestMapping("api/lost-items")
@AllArgsConstructor
public class LostItemWriteController {
    private final LostItemFacadeService facade;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LostItemEntity> createLostItem(@Valid @RequestBody LostItemCreateDto lostItem) {
        LostItemEntity item = facade.create(lostItem);

        return ResponseEntity
                .created(URI.create("/lost-items/" + item.getId()))
                .body(item);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LostItemEntity> updateLostItem (
        @PathVariable UUID id,
        @Valid @RequestBody LostItemUpdateDto lostItem
    ) {
        try {
            LostItemEntity item = facade.update(id, lostItem);
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLostItem(@PathVariable UUID id) {
        try {
            facade.deleteLostItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}