package com.example.lab2.LostItem.services.facade;

import com.example.lab2.LostItem.dto.request.LostItemCreateDto;
import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.LostItem.services.write.LostItemWriteService;
import org.springframework.stereotype.Service;

@Service
public class LostItemFacadeService {
    private final LostItemWriteService write;

    public LostItemFacadeService(LostItemWriteService write) {
        this.write = write;
    }

    public LostItemEntity create(LostItemCreateDto dto) {
        return this.write.create(dto);
    }
}