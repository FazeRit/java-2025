package com.example.lab2.LostItem.repository;

import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.common.interfaces.IAbstractRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// TODO: make abstract repository class
@Repository
public class LostItemRepository implements IAbstractRepository<LostItemEntity, UUID> {
    private List<LostItemEntity> items = new ArrayList<>();

    public void create(LostItemEntity lostItemEntity) {
        System.out.println(this.items);
        this.items.add(lostItemEntity);
        System.out.println(this.items);
    }

    @Override
    public List<LostItemEntity> getEntities(LostItemEntity... t) {
        return this.items;
    }

    @Override
    public Optional<LostItemEntity> getEntityById(UUID id) {
        return this.items.stream()
                .filter(li -> li.getId().equals(id))
                .findFirst();
    }
}