package com.example.lab2.lostItem.services.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.repository.LostItemRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LostItemWriteService {
    private final LostItemRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LostItemEntity create(LostItemCreateDto dto) {
        UUID itemId = UUID.randomUUID();

        LostItemEntity lostItemEntity = new LostItemEntity(
                dto.getName(),
                dto.getDescription(),
                dto.getTags(),
                dto.getLocation(),
                dto.getPhoneNumber()
        );
        lostItemEntity.setId(itemId);

        repository.create(lostItemEntity);

        return lostItemEntity;
    }

    public void delete(UUID id) throws LostItemNotFoundException {
        boolean deleted = repository.delete(id);
        if (!deleted) throw new LostItemNotFoundException(id);
    }

    public LostItemEntity update(UUID id, LostItemUpdateDto dto) throws LostItemNotFoundException {
        LostItemEntity lostItemEntity = new LostItemEntity(
                dto.getName(),
                dto.getDescription(),
                dto.getTags(),
                dto.getLocation(),
                dto.getPhoneNumber()
        );
        repository.update(id, lostItemEntity);
        return repository.getEntityById(id).orElseThrow(() -> new LostItemNotFoundException(id));
    }


    public LostItemEntity partialUpdate(UUID id, Map<String, Object> updates) throws LostItemNotFoundException {
        LostItemEntity existingItem = repository.getEntityById(id)
                .orElseThrow(() -> new LostItemNotFoundException(id));
        updates.forEach((key, value) -> {
            try {
                var field = LostItemEntity.class.getDeclaredField(key);
                field.setAccessible(true);
                Object convertedValue = objectMapper.convertValue(value, field.getType());
                field.set(existingItem, convertedValue);
            } catch (NoSuchFieldException ignored) {
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to update field: " + key, e);
            }
        });

        repository.update(id, existingItem);
        return existingItem;
    }
}