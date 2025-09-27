package com.example.lab2.lostItem.services.read;

import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.repository.LostItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LostItemReadService {
    private final LostItemRepository repository;

    public LostItemReadService(LostItemRepository repository) {
        this.repository = repository;
    }

    public List<LostItemEntity> getEntities(int page, int itemsPerPage) {
        return repository.getEntities(page, itemsPerPage);
    }

    public LostItemEntity getEntityById(UUID id) throws LostItemNotFoundException {
        return repository.getEntityById(id)
                .orElseThrow(() -> new LostItemNotFoundException(id));
    }

    public List<LostItemEntity> searchLostItems(String name, List<String> tags) {
        return repository.getEntities(1, 2).stream() // TODO: replace with a real pagination
                .filter(item -> (name == null || item.getName().toLowerCase().contains(name.toLowerCase())) &&
                        (tags == null || tags.isEmpty() || item.getTags() != null && item.getTags().stream().anyMatch(tags::contains)))
                .toList();
    }

    public int getTotalPages(int itemsPerPage) {
        return repository.getTotalPages(itemsPerPage);
    }
}