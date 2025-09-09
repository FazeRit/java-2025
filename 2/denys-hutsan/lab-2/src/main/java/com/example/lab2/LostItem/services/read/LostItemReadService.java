package com.example.lab2.LostItem.services.read;

import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.LostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.LostItem.repository.LostItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LostItemReadService {
    private final LostItemRepository repository;

    public LostItemReadService(LostItemRepository repository) {
        this.repository = repository;
    }

    public List<LostItemEntity> getEntities() {
        return this.repository.getEntities();
    }

    public LostItemEntity getEntityById(UUID id) throws LostItemNotFoundException {
        return this.repository.getEntityById(id)
                .orElseThrow(() -> new LostItemNotFoundException(id));
    }
}