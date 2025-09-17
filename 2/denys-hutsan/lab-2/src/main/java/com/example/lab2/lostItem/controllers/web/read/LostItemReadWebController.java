package com.example.lab2.lostItem.controllers.web.read;

import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/lost-items")
public class LostItemReadWebController {

    LostItemFacadeService facade;

    public LostItemReadWebController(LostItemFacadeService facade) {
        this.facade = facade;
    }

    @GetMapping("/")
    public String getLostItems(Model model) {
        var items = facade.getEntities();
        model.addAttribute("items", items);
        return "lost-items-list";
    }

    @GetMapping("/{id}")
    public String getLostItemById(@PathVariable() UUID id, Model model) {
        try {
            model.addAttribute("lostItem", facade.getEntityById(id));
            return "lost-item";
        } catch (LostItemNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public String searchLostItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> tags,
            Model model
    ) {
        var items = facade.searchLostItems(name, tags);
        model.addAttribute("items", items);
        return "lost-items-list";
    }
}
