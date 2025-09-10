package com.example.lab2.lostItem.controllers.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

// TODO: make exception handler
@RestController
@RequestMapping("lost-items")
public class LostItemWriteController {
    private final LostItemFacadeService facade;

    public LostItemWriteController(LostItemFacadeService facade) {
        this.facade = facade;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LostItemEntity> createLostItem(@Valid @RequestBody LostItemCreateDto lostItem) {
        LostItemEntity item = this.facade.create(lostItem);

        return ResponseEntity
                .created(URI.create("/lost-items/" + item.getId()))
                .body(item);
    }
}