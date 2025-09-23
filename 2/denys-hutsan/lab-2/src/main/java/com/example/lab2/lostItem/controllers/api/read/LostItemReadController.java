package com.example.lab2.lostItem.controllers.api.read;

import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/lost-items")
@AllArgsConstructor
public class LostItemReadController {
    private final LostItemFacadeService facade;


    @GetMapping("/")
    public ResponseEntity<List<LostItemEntity>> getLostItems() {
        List<LostItemEntity> items  = facade.getEntities();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LostItemEntity> getLostItemById(@PathVariable() UUID id) {
        try {
            LostItemEntity item = facade.getEntityById(id);
            return ResponseEntity.ok(item);
        } catch (LostItemNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<LostItemEntity>> searchLostItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> tags
    ) {
        List<LostItemEntity> itemEntities = facade.searchLostItems(name, tags);
        return ResponseEntity.ok(itemEntities);
    }
}