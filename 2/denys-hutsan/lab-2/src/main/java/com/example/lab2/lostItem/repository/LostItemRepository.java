package com.example.lab2.lostItem.repository;

import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.common.interfaces.AbstractRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

// TODO: make abstract repository class
@Repository
@AllArgsConstructor
public class LostItemRepository implements AbstractRepository<LostItemEntity, UUID> {
    private final List<LostItemEntity> items;

    public void create(LostItemEntity lostItemEntity) {
        System.out.println(items);
        items.add(lostItemEntity);
        System.out.println(items);
    }

    @Override
    public List<LostItemEntity> getEntities(LostItemEntity... t) {
        return items;
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

    private List<LostItemEntity> setTestItems() {
        List<LostItemEntity> items = new ArrayList<>();

        items.add(new LostItemEntity(UUID.randomUUID(),"Phone", "iPhone 12", List.of("phone", "tablet", "red"), "123 Street", "555-1234"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Keys", "Set of car keys", List.of("red", "car"), "456 glasses", "555-5678"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Backpack", "Blue backpack with laptop", List.of("backpack", "glasses", "laptop"), "789 Boulevard", "555-9012"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Watch", "Silver wristwatch", List.of("red", "silver", "tablet"), "321 Road", "555-3456"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Wallet", "Black leather wallet", List.of("wallet", "black", "android"), "654 Lane", "555-7890"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Glasses", "Pair of reading glasses", List.of("glasses", "reading"), "987 Drive", "555-2345"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Umbrella", "Red umbrella", List.of("umbrella", "red"), "147 Path", "555-6789"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Headphones", "Wireless headphones", List.of("tablet", "android"), "258 Terrace", "555-0123"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Book", "Hardcover novel", List.of("book", "novel", "hardcover"), "369 Court", "555-4567"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Camera", "Digital camera", List.of("camera", "digital"), "159 Circle", "555-8901"));
        items.add(new LostItemEntity(UUID.randomUUID(),"Tablet", "Android tablet", List.of("glasses", "android"), "753 Square", "555-2345"));

        return items;
    }
}

