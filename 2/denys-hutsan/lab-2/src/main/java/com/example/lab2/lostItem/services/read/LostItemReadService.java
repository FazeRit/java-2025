package com.example.lab2.lostItem.services.read;

import com.example.lab2.lostItem.entity.LostItem;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.entity.TagEntity;
import com.example.lab2.lostItem.exceptions.InvalidPageParamsException;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import com.example.lab2.lostItem.repository.LostItemRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class LostItemReadService {

    private final LostItemRepository repository;

    private LostItem toLostItemDto(LostItemEntity entity) {
        return new LostItem(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPhoneNumber(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getLocation(),
                entity.getTagNames()
        );
    }

    public List<LostItem> getEntities(int page, int itemsPerPage) throws InvalidPageParamsException {
        if (page < 1 || itemsPerPage < 1) {
            throw new InvalidPageParamsException();
        }

        Pageable pageable = PageRequest.of(page - 1, itemsPerPage, Sort.by("createdAt").descending());

        Page<LostItemEntity> entityPage = repository.findAll(pageable);

        return entityPage.getContent().stream()
                .map(this::toLostItemDto)
                .toList();
    }

    public LostItem getEntityById(Integer id) throws LostItemNotFoundException {
        return repository.findById(id)
                .map(this::toLostItemDto)
                .orElseThrow(() -> new LostItemNotFoundException(id));
    }

    public List<LostItem> searchLostItems(String name, List<String> tags, int page, int itemsPerPage) throws InvalidPageParamsException {
        if (page < 1 || itemsPerPage < 1) {
            throw new InvalidPageParamsException();
        }

        Specification<LostItemEntity> spec = createSearchSpecification(name, tags);

        Pageable pageable = PageRequest.of(page - 1, itemsPerPage, Sort.by("createdAt").descending());

        Page<LostItemEntity> entityPage = repository.findAll(spec, pageable);

        return entityPage.getContent().stream()
                .map(this::toLostItemDto)
                .toList();
    }

    private Specification<LostItemEntity> createSearchSpecification(String name, List<String> tags) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (tags != null && !tags.isEmpty()) {
                Join<LostItemEntity, TagEntity> tagJoin = root.join("tags");
                predicates.add(tagJoin.get("name").in(tags));
            }

            query.distinct(true);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    public int getTotalPages(int itemsPerPage) {
        long totalItems = repository.count();
        if (itemsPerPage <= 0) return 0;
        return (int) Math.ceil((double) totalItems / itemsPerPage);
    }

    public List<LostItem> getItemsByNameContaining(String namePart) {
        return repository.findByNameContaining(namePart).stream()
                .map(this::toLostItemDto)
                .toList();
    }

    public List<LostItem> getItemsByLocation(String location) {
        return repository.findAllByLocation(location).stream()
                .map(this::toLostItemDto)
                .toList();
    }

    public LostItem getItemByPhoneNumber(String phoneNumber) throws LostItemNotFoundException {
        LostItemEntity entity = repository.findByPhoneNumber(phoneNumber);
        if (entity == null) {
            throw new LostItemNotFoundException("No item found with phone number: " + phoneNumber);
        }
        return toLostItemDto(entity);
    }

    public List<LostItem> getItemsByTagNames(List<String> tagNames) {
        return repository.findByTags_NameIn(tagNames).stream()
                .map(this::toLostItemDto)
                .toList();
    }
}