package com.example.lab2.lostItem.controllers.web.read;

import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/lost-items")
@AllArgsConstructor
public class LostItemReadWebController {

    LostItemFacadeService facade;

    @GetMapping("/")
    public String getLostItems(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int itemsPerPage,
            Model model) {

        var items = facade.getEntities(page,  itemsPerPage);
        int totalPages = facade.getTotalPages(itemsPerPage);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);

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
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int itemsPerPage,
            Model model
    ) {

        var items = facade.searchLostItems(name, tags, page, itemsPerPage);

        List<String> parts = new ArrayList<>();

        if (name != null && !name.isBlank()) {
            parts.add(name);
        }
        if (tags != null && !tags.isEmpty()) {
            parts.addAll(tags);
        }

        String searchQuery = String.join(", ", parts);

        model.addAttribute("items", items);
        model.addAttribute("currentPage", page);
        model.addAttribute("itemsPerPage", itemsPerPage);
        model.addAttribute("searchQuery", searchQuery);
        if (tags != null) model.addAttribute("tags", tags);
        if (name != null) model.addAttribute("name", name);

        return "lost-items-list";
    }
}
