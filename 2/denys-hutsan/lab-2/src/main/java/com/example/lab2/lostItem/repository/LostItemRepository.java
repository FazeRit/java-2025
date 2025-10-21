package com.example.lab2.lostItem.repository;

import com.example.lab2.common.interfaces.AbstractRepository;
import com.example.lab2.lostItem.entity.LostItemEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// TODO: make abstract repository class
@Repository
@AllArgsConstructor
public class LostItemRepository implements AbstractRepository<LostItemEntity, Integer> {
    private final List<LostItemEntity> items;

    public void create(LostItemEntity lostItemEntity) {
        System.out.println(items);
        items.add(lostItemEntity);
        System.out.println(items);
    }

    @Override
    public List<LostItemEntity> getEntities(int page, int itemsPerPage) {

        int fromIndex = (page - 1) * itemsPerPage;

        if (fromIndex >= items.size()) {
            return new ArrayList<>();
        }

        int toIndex = Math.min(fromIndex + itemsPerPage, items.size());

        return items.subList(fromIndex, toIndex);
    }

    public List<LostItemEntity> getAllEntities() {
        return items;
    }

    @Override
    public Optional<LostItemEntity> getEntityById(Integer id) {
        return items.stream()
                .filter(li -> li.getId().equals(id))
                .findFirst();
    }

    public boolean delete(Integer id) {
        return items.removeIf(li -> li.getId().equals(id));
    }

    public void update(Integer id, LostItemEntity lostItemEntity) {
        items.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .ifPresent(item -> {
                    item.setName(lostItemEntity.getName());
                    item.setDescription(lostItemEntity.getDescription());
                    item.setTags(lostItemEntity.getTags());
                    item.setLocation(lostItemEntity.getLocation());
                    item.setPhoneNumber(lostItemEntity.getPhoneNumber());
                    item.setUpdatedAt(LocalDateTime.now());
                });
    }

    public int getTotalPages(int itemsPerPage) {
        return (int) Math.ceil((double) items.size() / itemsPerPage);
    }
}

