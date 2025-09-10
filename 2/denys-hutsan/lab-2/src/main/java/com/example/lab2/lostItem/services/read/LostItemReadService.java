package com.example.lab2.lostItem.services.read;

import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.repository.LostItemRepository;
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

    public LostItemEntity getEntityByName(String name) throws LostItemNotFoundException {
        return this.repository.getEntities().stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new LostItemNotFoundException(name));
    }

    public List<LostItemEntity> getEntitiesByTag(String tag) {
        return this.repository.getEntities().stream()
                .filter(item -> item.getTags().contains(tag))
                .toList();
    }

    public List<LostItemEntity> getEntitiesByName(String name){
        return this.repository.getEntities().stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .toList();
    }

}