package com.example.lab2.LostItem.services.facade;

import com.example.lab2.LostItem.dto.request.LostItemCreateDto;
import com.example.lab2.LostItem.entity.LostItemEntity;
import com.example.lab2.LostItem.services.read.LostItemReadService;
import com.example.lab2.LostItem.services.write.LostItemWriteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostItemFacadeService {
    private final LostItemWriteService write;

    private final LostItemReadService read;

    public LostItemFacadeService(LostItemWriteService write, LostItemReadService read) {
        this.write = write;
        this.read = read;
    }

    public LostItemEntity create(LostItemCreateDto dto) {
        return this.write.create(dto);
    }

    public List<LostItemEntity> getEntities() {
        return this.read.getEntities();
    }
}