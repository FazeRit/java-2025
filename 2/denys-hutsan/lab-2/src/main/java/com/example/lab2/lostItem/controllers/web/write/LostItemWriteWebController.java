package com.example.lab2.lostItem.controllers.web.write;

import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Controller
@RequestMapping("/lost-items")
public class LostItemWriteWebController {
    private final LostItemFacadeService facade;

    public LostItemWriteWebController(LostItemFacadeService facade) {
        this.facade = facade;
    }

    @PostMapping("/{id}/delete")
    public String deleteLostItem(@PathVariable UUID id) {
        try {
        facade.deleteLostItem(id);
        return "redirect:/lost-items/";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
