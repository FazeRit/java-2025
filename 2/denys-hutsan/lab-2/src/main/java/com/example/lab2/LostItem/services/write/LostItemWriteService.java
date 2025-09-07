package com.example.lab2.LostItem.services.write;

import com.example.lab2.LostItem.dto.request.LostItemCreateDto;
import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.LostItem.repository.LostItemRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LostItemWriteService {
    private final LostItemRepository repository;

    public LostItemWriteService(LostItemRepository repository) {
        this.repository = repository;
    }

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

        this.repository.create(lostItemEntity);

        return lostItemEntity;
    }
}