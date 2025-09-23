package com.example.lab2.lostItem.services.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.repository.LostItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LostItemWriteService {
    private final LostItemRepository repository;

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
}