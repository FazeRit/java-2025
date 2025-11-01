package com.example.lab2.lostItem.services.facade;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItem;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.InvalidPageParamsException;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.services.read.LostItemReadService;
import com.example.lab2.lostItem.services.write.LostItemWriteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LostItemFacadeService {
    private final LostItemWriteService write;

    private final LostItemReadService read;

    public LostItemEntity create(LostItemCreateDto dto) {
        return write.create(dto);
    }

    public List<LostItem> getEntities(int page, int itemsPerPage) throws InvalidPageParamsException {
        return read.getEntities(page, itemsPerPage);
    }

    public LostItemEntity update(Integer id, LostItemUpdateDto dto, boolean transactionFail) throws Exception {
        return write.update(id, dto, transactionFail);
    }

    public int getTotalPages(int itemsPerPage) {
        return read.getTotalPages(itemsPerPage);
    }

    public LostItem getEntityById(Integer id) throws LostItemNotFoundException {
        return read.getEntityById(id);
    }

    public List<LostItem> searchLostItems(String name, List<String> tags, int page, int itemsPerPage) throws InvalidPageParamsException {
        return read.searchLostItems(name, tags, page, itemsPerPage);
    }

    public void deleteLostItem(Integer id) throws LostItemNotFoundException {
        write.delete(id);
    }

    public List<LostItem> getItemsByNameContaining(String namePart) {
        return read.getItemsByNameContaining(namePart);
    }

    public List<LostItem> getItemsByLocation(String location) {
        return read.getItemsByLocation(location);
    }

    public LostItem getItemByPhoneNumber(String phoneNumber) throws LostItemNotFoundException {
        return read.getItemByPhoneNumber(phoneNumber);
    }

    public List<LostItem> getItemsByTagNames(List<String> tagNames) {
        return read.getItemsByTagNames(tagNames);
    }
}