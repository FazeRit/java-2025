package com.example.lab2.LostItem.controllers.read;

import com.example.lab2.LostItem.services.facade.LostItemFacadeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lost-items")
public class LostItemReadController {
    private final LostItemFacadeService facade;

    public LostItemReadController(LostItemFacadeService facade) {
        this.facade = facade;
    }
}