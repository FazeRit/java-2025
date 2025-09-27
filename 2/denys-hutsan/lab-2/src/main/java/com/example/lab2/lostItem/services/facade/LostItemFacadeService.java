package com.example.lab2.lostItem.services.facade;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.services.read.LostItemReadService;
import com.example.lab2.lostItem.services.write.LostItemWriteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LostItemFacadeService {
    private final LostItemWriteService write;

    private final LostItemReadService read;

    public LostItemFacadeService(LostItemWriteService write, LostItemReadService read) {
        this.write = write;
        this.read = read;
    }

    public LostItemEntity create(LostItemCreateDto dto) {
        return write.create(dto);
    }

    public List<LostItemEntity> getEntities(int page, int itemsPerPage) {
        return read.getEntities(page, itemsPerPage);
    }

    public int getTotalPages(int itemsPerPage) { return  read.getTotalPages(itemsPerPage); }

    public LostItemEntity getEntityById(UUID id) throws LostItemNotFoundException {
        return read.getEntityById(id);
    }

    public List<LostItemEntity> searchLostItems(String name, List<String> tags) {
        return read.searchLostItems(name, tags);
    }

    public void deleteLostItem(UUID id) throws LostItemNotFoundException {
        write.delete(id);
    }
}