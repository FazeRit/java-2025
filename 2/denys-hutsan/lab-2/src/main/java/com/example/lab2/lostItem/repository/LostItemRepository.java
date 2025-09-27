package com.example.lab2.lostItem.repository;

import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.common.interfaces.AbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// TODO: make abstract repository class
@Repository
public class LostItemRepository implements AbstractRepository<LostItemEntity, UUID> {
    private final List<LostItemEntity> items = new ArrayList<>();

    public void create(LostItemEntity lostItemEntity) {
        System.out.println(items);
        items.add(lostItemEntity);
        System.out.println(items);
    }

    @Override
    public List<LostItemEntity> getEntities(int page, int itemsPerPage) {
        if (page < 1 || itemsPerPage < 1) {
            throw new IllegalArgumentException("Page and itemsPerPage must be greater than 0");
        }

        int fromIndex = (page - 1) * itemsPerPage;

        if (fromIndex >= items.size()) {
            return new ArrayList<>();
        }

        int toIndex = Math.min(fromIndex + itemsPerPage, items.size());

        return items.subList(fromIndex, toIndex);
    }

    @Override
    public Optional<LostItemEntity> getEntityById(UUID id) {
        return items.stream()
                .filter(li -> li.getId().equals(id))
                .findFirst();
    }

    public boolean delete(UUID id) {
        return items.removeIf(li -> li.getId().equals(id));
    }

    public int getTotalPages(int itemsPerPage) {
        return (int) Math.ceil((double) items.size() / itemsPerPage);
    }
}