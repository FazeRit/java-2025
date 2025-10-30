//package com.example.lab2.lostItem.services.read;
//
//import com.example.lab2.lostItem.entity.LostItemEntity;
//import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
//import com.example.lab2.lostItem.repository.LostItemRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.UUID;
//
//@Service
//@AllArgsConstructor
//public class LostItemReadService {
//    private final LostItemRepository repository;
//
//    public List<LostItemEntity> getEntities(int page, int itemsPerPage) {
//        return repository.getEntities(page, itemsPerPage);
//    }
//
//    public LostItemEntity getEntityById(UUID id) throws LostItemNotFoundException {
//        return repository.getEntityById(id)
//                .orElseThrow(() -> new LostItemNotFoundException(id));
//    }
//
//    public List<LostItemEntity> searchLostItems(String name, List<String> tags, int page, int itemsPerPage) {
//        List<LostItemEntity> filteredItems = repository.getAllEntities().stream()
//                .filter(item -> (name == null || item.getName().toLowerCase().contains(name.toLowerCase())) &&
//                        (tags == null || tags.isEmpty() || item.getTags() != null && item.getTags().stream().anyMatch(tags::contains)))
//                .toList();
//
//        int toIndex = Math.min((page - 1) * itemsPerPage + itemsPerPage, filteredItems.size());
//
//        return filteredItems.subList(0, toIndex);
//    }
//
//    public int getTotalPages(int itemsPerPage) {
//        return repository.getTotalPages(itemsPerPage);
//    }
//}

package com.example.lab2.lostItem.services.read;

import com.example.lab2.lostItem.entity.LostItem;
import com.example.lab2.lostItem.exceptions.InvalidPageParamsException;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LostItemReadService {

    private static final RowMapper<LostItem> LOST_ITEM_ROW_MAPPER = (rs, rowNum) -> new LostItem(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("phone_number"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime(),
            rs.getString("location"),
            null
    );
    private final JdbcTemplate jdbcTemplate;

    public LostItemReadService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LostItem> getEntities(int page, int itemsPerPage) throws InvalidPageParamsException {
        if (page < 1 || itemsPerPage < 1) {
            throw new InvalidPageParamsException();
        }

        int offset = (page - 1) * itemsPerPage;

        String sql = """
                SELECT li.id, li.name, li.description, li.phone_number,
                       li.created_at, li.updated_at, li.location
                FROM lost_items li
                ORDER BY li.created_at DESC
                LIMIT ? OFFSET ?
                """;

        List<LostItem> items = jdbcTemplate.query(sql, LOST_ITEM_ROW_MAPPER, itemsPerPage, offset);
        return items.stream()
                .map(this::attachTags)
                .toList();
    }

    public LostItem getEntityById(Integer id) throws LostItemNotFoundException {
        String sql = """
                SELECT li.id, li.name, li.description, li.phone_number,
                       li.created_at, li.updated_at, li.location
                FROM lost_items li
                WHERE li.id = ?
                """;

        List<LostItem> result = jdbcTemplate.query(sql, LOST_ITEM_ROW_MAPPER, id);
        return result.stream()
                .findFirst()
                .map(this::attachTags)
                .orElseThrow(() -> new LostItemNotFoundException(id));
    }

    public List<LostItem> searchLostItems(String name, List<String> tags, int page, int itemsPerPage) throws InvalidPageParamsException {
        if (page < 1 || itemsPerPage < 1) {
            throw new InvalidPageParamsException();
        }

        int offset = (page - 1) * itemsPerPage;

        StringBuilder sql = new StringBuilder("""
        SELECT DISTINCT li.id, li.name, li.description, li.phone_number,
                        li.created_at, li.updated_at, li.location
        FROM lost_items li
        LEFT JOIN lost_item_tags lit ON li.id = lit.lost_item_id::INTEGER
        LEFT JOIN tags t ON lit.tag_id = t.id
        WHERE 1=1
        """);

        List<Object> params = new ArrayList<>();

        if (name != null && !name.isBlank()) {
            sql.append(" AND LOWER(li.name) LIKE LOWER(?)");
            params.add("%" + name + "%");
        }

        if (tags != null && !tags.isEmpty()) {
            sql.append(" AND t.name IN (")
                    .append(String.join(",", tags.stream().map(t -> "?").toList()))
                    .append(")");
            params.addAll(tags);
        }

        sql.append(" ORDER BY li.created_at DESC LIMIT ? OFFSET ?");
        params.add(itemsPerPage);
        params.add(offset);

        List<LostItem> items = jdbcTemplate.query(sql.toString(), LOST_ITEM_ROW_MAPPER, params.toArray());
        return items.stream()
                .map(this::attachTags)
                .toList();
    }

    public int getTotalPages(int itemsPerPage) {
        String sql = "SELECT COUNT(*) FROM lost_items";
        Integer total = jdbcTemplate.queryForObject(sql, Integer.class);
        return (total == 0) ? 0 : (int) Math.ceil((double) total / itemsPerPage);
    }

    private LostItem attachTags(LostItem item) {
        String tagSql = """
                SELECT t.name
                FROM tags t
                JOIN lost_item_tags lit ON t.id = lit.tag_id
                WHERE lit.lost_item_id = ?
                """;

        List<String> tagNames = jdbcTemplate.queryForList(tagSql, String.class, item.id());
        return new LostItem(
                item.id(),
                item.name(),
                item.description(),
                item.phoneNumber(),
                item.createdAt(),
                item.updatedAt(),
                item.location(),
                tagNames
        );
    }
}