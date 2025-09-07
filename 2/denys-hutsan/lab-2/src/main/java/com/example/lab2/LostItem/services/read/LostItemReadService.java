package com.example.lab2.LostItem.services.read;

import com.example.lab2.LostItem.repository.LostItemRepository;
import org.springframework.stereotype.Service;

@Service
public class LostItemReadService {
    private final LostItemRepository repository;

    public LostItemReadService(LostItemRepository repository) {
        this.repository = repository;
    }
}