package com.example.lab2.lostItem.services.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.entity.TagEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.repository.LostItemRepository;
import com.example.lab2.lostItem.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class LostItemWriteService {

    private final LostItemRepository itemRepository;
    private final TagRepository tagRepository;

    private Set<TagEntity> findOrCreateTags(List<String> tagNames) {
        if (tagNames == null || tagNames.isEmpty()) {
            return new HashSet<>();
        }

        List<TagEntity> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream()
                .map(TagEntity::getName)
                .collect(Collectors.toSet());

        List<TagEntity> newTags = tagNames.stream()
                .filter(name -> !existingTagNames.contains(name))
                .map(TagEntity::new)
                .toList();

        tagRepository.saveAll(newTags);

        Set<TagEntity> allTags = new HashSet<>(existingTags);
        allTags.addAll(newTags);
        return allTags;
    }

    public LostItemEntity create(LostItemCreateDto dto) {
        Set<TagEntity> tags = findOrCreateTags(dto.getTags());

        LostItemEntity entity = new LostItemEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLocation(dto.getLocation());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setTags(tags);

        return itemRepository.save(entity);
    }

    public void delete(Integer id) throws LostItemNotFoundException {
        if (!itemRepository.existsById(id)) {
            throw new LostItemNotFoundException(id);
        }
        itemRepository.deleteById(id);
    }

    public LostItemEntity update(Integer id, LostItemUpdateDto dto, boolean transactionFail) throws Exception {
        if (transactionFail) {
            throw new Exception("Internal exception simulation");
        }

        LostItemEntity entity = itemRepository.findById(id)
                .orElseThrow(() -> new LostItemNotFoundException(id));

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getLocation() != null) {
            entity.setLocation(dto.getLocation());
        }
        if (dto.getPhoneNumber() != null) {
            entity.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getTags() != null) {
            entity.setTags(findOrCreateTags(dto.getTags()));
        }

        return itemRepository.save(entity);
    }
}