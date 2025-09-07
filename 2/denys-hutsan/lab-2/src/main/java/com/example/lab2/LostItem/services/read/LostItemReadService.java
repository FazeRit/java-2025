package com.example.lab2.LostItem.services.read;

import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.LostItem.repository.LostItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostItemReadService {
    private final LostItemRepository repository;

    public LostItemReadService(LostItemRepository repository) {
        this.repository = repository;
    }

    public List<LostItemEntity> getEntities() {
        return this.repository.getEntities();
    }
}