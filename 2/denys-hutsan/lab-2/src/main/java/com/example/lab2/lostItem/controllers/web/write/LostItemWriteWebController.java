package com.example.lab2.lostItem.controllers.web.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.services.facade.LostItemFacadeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Controller
@RequestMapping("/lost-items")
@AllArgsConstructor
public class LostItemWriteWebController {
    private static final String CREATE_VIEW = "lost-item-create";
    private static final String EDIT_VIEW = "lost-item-edit";
    private final LostItemFacadeService facade;

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("lostItemCreateDto", new LostItemCreateDto());
        return CREATE_VIEW;
    }

    @PostMapping("/create")
    public String createLostItem(
        @Valid @ModelAttribute("lostItemCreateDto") LostItemCreateDto lostItemCreateDto,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return CREATE_VIEW;
        }

        try {
            LostItemEntity createdItem = facade.create(lostItemCreateDto);
            return "redirect:/lost-items/" + createdItem.getId();
        } catch (Exception e) {
            model.addAttribute("error", "Failed to create lost item: " + e.getMessage());
            return CREATE_VIEW;
        }
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable UUID id, Model model) {
        try {
            LostItemEntity item = facade.getEntityById(id);
            LostItemUpdateDto updateDto = new LostItemUpdateDto();
            updateDto.setName(item.getName());
            updateDto.setDescription(item.getDescription());
            updateDto.setLocation(item.getLocation());
            updateDto.setPhoneNumber(item.getPhoneNumber());
            updateDto.setTags(item.getTags());

            model.addAttribute("lostItemUpdateDto", updateDto);
            model.addAttribute("itemId", id);
            return EDIT_VIEW;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/edit")
    public String updateLostItem(
        @PathVariable UUID id,
        @Valid @ModelAttribute("lostItemUpdateDto") LostItemUpdateDto lostItemUpdateDto,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("itemId", id);
            return EDIT_VIEW;
        }

        try {
            LostItemEntity updatedItem = facade.update(id, lostItemUpdateDto);
            return "redirect:/lost-items/" + updatedItem.getId();
        } catch (Exception e) {
            model.addAttribute("error", "Failed to update lost item: " + e.getMessage());
            model.addAttribute("itemId", id);
            return EDIT_VIEW;
        }
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