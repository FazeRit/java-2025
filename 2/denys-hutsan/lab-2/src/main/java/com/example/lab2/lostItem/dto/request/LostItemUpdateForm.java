package com.example.lab2.lostItem.dto.request;

import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Setter @Getter
public class LostItemUpdateForm {
    @NotBlank(message = "Назва обов'язкова")
    private String name;

    private String description;

    @NotEmpty(message = "Додайте хоча б один тег")
    private String tags;

    private String location;

    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "Невірний формат телефону")
    private String phoneNumber;

    public LostItemUpdateDto toDto() {
        List<String> tagList = tags != null && !tags.isBlank()
                ? Arrays.stream(tags.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList()
                : List.of();

        return new LostItemUpdateDto(name, description, tagList, location, phoneNumber);
    }

    public static LostItemUpdateForm from(LostItem item) {
        LostItemUpdateForm form = new LostItemUpdateForm();
        form.setName(item.name());
        form.setDescription(item.description());
        form.setTags(item.tags() != null ? String.join(", ", item.tags()) : "");
        form.setLocation(item.location());
        form.setPhoneNumber(item.phoneNumber());
        return form;
    }
}