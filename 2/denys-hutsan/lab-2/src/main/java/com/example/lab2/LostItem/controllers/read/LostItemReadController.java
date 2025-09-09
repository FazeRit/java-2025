package com.example.lab2.LostItem.controllers.read;

import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.LostItem.services.facade.LostItemFacadeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("lost-items")
public class LostItemReadController {
    private final LostItemFacadeService facade;

    public LostItemReadController(LostItemFacadeService facade) {
        this.facade = facade;
    }

    @GetMapping("/")
    public ResponseEntity<List<LostItemEntity>> getLostItems() {
        List<LostItemEntity> items = this.facade.getEntities();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLostItemById(@PathVariable("id") UUID id) {
        try {
            LostItemEntity lostItem = facade.getEntityById(id);
            return ResponseEntity.ok(lostItem);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}