package com.example.lab2.lostItem.controllers.read;

import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
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
        List<LostItemEntity> items = facade.getEntities();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LostItemEntity> getLostItemById(@PathVariable("id") UUID id) {
        try {
            LostItemEntity lostItem = facade.getEntityById(id);
            return ResponseEntity.ok(lostItem);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<LostItemEntity>> searchLostItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> tags
    ) {
        List<LostItemEntity> items = facade.searchLostItems(name, tags);
        return ResponseEntity.ok(items);
    }


    @GetMapping("/get-item/{name}")
    public ResponseEntity<LostItemEntity> getLostItemByName(@PathVariable("name") String name) {
        try {
            LostItemEntity lostItem = facade.getEntityByName(name);
            return ResponseEntity.ok(lostItem);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-items-by-tag/{tag}")
    public ResponseEntity<List<LostItemEntity>> getLostItemsByTag(@PathVariable("tag") String tag) {
        List<LostItemEntity> items = facade.getEntitiesByTag(tag);
        return ResponseEntity.ok(items);
    }

    @GetMapping("/get-items-by-name/{name}")
    public ResponseEntity<List<LostItemEntity>> getLostItemsByName(@PathVariable("name") String name) {
        List<LostItemEntity> items = facade.getEntitiesByName(name);
        return ResponseEntity.ok(items);
    }
}