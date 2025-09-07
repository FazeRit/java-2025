package com.example.lab2.LostItem.controllers.read;

import com.example.lab2.LostItem.dto.request.LostItemCreateDto;
import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.LostItem.services.facade.LostItemFacadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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
}