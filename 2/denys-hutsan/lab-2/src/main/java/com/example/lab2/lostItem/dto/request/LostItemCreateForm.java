package com.example.lab2.lostItem.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter @Getter
@NoArgsConstructor
public class LostItemCreateForm {
    private String name;
    private String description;
    private String tags;
    private String location;
    private String phoneNumber;

    public LostItemCreateDto toDto() {
        List<String> tagList = tags != null && !tags.isBlank()
                ? Arrays.stream(tags.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList()
                : List.of();

        LostItemCreateDto dto = new LostItemCreateDto();
        dto.setName(name);
        dto.setDescription(description);
        dto.setTags(tagList);
        dto.setLocation(location);
        dto.setPhoneNumber(phoneNumber);

        return dto;
    }
}
