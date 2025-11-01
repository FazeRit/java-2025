package com.example.lab2.lostItem.controllers.api.read;

import com.example.lab2.lostItem.docs.api.read.LostItemReadApiDoc;
import com.example.lab2.lostItem.entity.LostItem;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.InvalidPageParamsException;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/lost-items")
@AllArgsConstructor
public class LostItemReadController implements LostItemReadApiDoc {
    private final LostItemFacadeService facade;

    @GetMapping("/")
    @Override
    public ResponseEntity<List<LostItem>> getLostItems(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int itemsPerPage
    ) {
        try
        {
            List<LostItem> items = facade.getEntities(page, itemsPerPage);
            return ResponseEntity.ok(items);
        } catch (InvalidPageParamsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<LostItem> getLostItemById(@PathVariable() Integer id) {
        try {
            LostItem item = facade.getEntityById(id);
            return ResponseEntity.ok(item);
        } catch (LostItemNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<List<LostItem>> searchLostItems(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int itemsPerPage,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> tags
    ) {
        try
        {
            List<LostItem> itemEntities = facade.searchLostItems(name, tags, page, itemsPerPage);
            return ResponseEntity.ok(itemEntities);
        } catch (InvalidPageParamsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/find/by-name")
    public ResponseEntity<List<LostItem>> getItemsByNameQuery(@RequestParam String name) {
        return ResponseEntity.ok(facade.getItemsByNameContaining(name));
    }

    @GetMapping("/find/by-location")
    public ResponseEntity<List<LostItem>> getItemsByLocationQuery(@RequestParam String location) {
        return ResponseEntity.ok(facade.getItemsByLocation(location));
    }

    @GetMapping("/find/by-phone")
    public ResponseEntity<LostItem> getItemByPhoneQuery(@RequestParam String phone) {
        try {
            return ResponseEntity.ok(facade.getItemByPhoneNumber(phone));
        } catch (LostItemNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/find/by-tags")
    public ResponseEntity<List<LostItem>> getItemsByTagsQuery(@RequestParam List<String> tags) {
        return ResponseEntity.ok(facade.getItemsByTagNames(tags));
    }
}