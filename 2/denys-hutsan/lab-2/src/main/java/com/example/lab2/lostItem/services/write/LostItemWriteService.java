package com.example.lab2.lostItem.services.write;

import com.example.lab2.lostItem.dto.request.LostItemCreateDto;
import com.example.lab2.lostItem.dto.request.LostItemUpdateDto;
import com.example.lab2.lostItem.entity.LostItemEntity;
import com.example.lab2.lostItem.exceptions.LostItemNotFoundException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LostItemWriteService {

    private final JdbcClient jdbcClient;

    public LostItemWriteService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Transactional
    public LostItemEntity create(LostItemCreateDto dto) {
        String insertItemSql = """
        INSERT INTO lost_items (name, description, phone_number, location)
        VALUES (:name, :description, :phoneNumber, :location)
        RETURNING id
        """;

        Integer itemId = jdbcClient.sql(insertItemSql)
                .param("name", dto.name())
                .param("description", dto.description())
                .param("phoneNumber", dto.phoneNumber())
                .param("location", dto.location())
                .query(Integer.class)
                .single();

        if (dto.tags() != null && !dto.tags().isEmpty()) {
            String upsertTagSql = """
            INSERT INTO tags (name) VALUES (:name)
            ON CONFLICT (name) DO UPDATE SET name = EXCLUDED.name
            RETURNING id
            """;

            String insertLinkSql = """
            INSERT INTO lost_item_tags (lost_item_id, tag_id)
            VALUES (:itemId, :tagId)
            ON CONFLICT (lost_item_id, tag_id) DO NOTHING
            """;

            for (String tagName : dto.tags()) {
                Integer tagId = jdbcClient.sql(upsertTagSql)
                        .param("name", tagName)
                        .query(Integer.class)
                        .single();

                jdbcClient.sql(insertLinkSql)
                        .param("itemId", itemId)
                        .param("tagId", tagId)
                        .update();
            }
        }

        LostItemEntity entity = new LostItemEntity(
                dto.name(),
                dto.description(),
                dto.tags() != null ? new ArrayList<>(dto.tags()) : new ArrayList<>(),
                dto.location(),
                dto.phoneNumber()
        );
        entity.setId(itemId);
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());

        return entity;
    }

    @Transactional
    public void delete(Integer id) throws LostItemNotFoundException {
        boolean exists = jdbcClient.sql("SELECT 1 FROM lost_items WHERE id = :id")
                .param("id", id)
                .query(Integer.class)
                .optional()
                .isPresent();

        if (!exists) {
            throw new LostItemNotFoundException(id);
        }

        jdbcClient.sql("DELETE FROM lost_items WHERE id = :id")
                .param("id", id)
                .update();
    }

    @Transactional
    public LostItemEntity update(Integer id, LostItemUpdateDto dto) throws LostItemNotFoundException {
        if (jdbcClient.sql("SELECT 1 FROM lost_items WHERE id = :id")
                .param("id", id)
                .query(Integer.class)
                .optional()
                .isEmpty()) {
            throw new LostItemNotFoundException(id);
        }

        List<String> setParts = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (dto.name() != null) {
            setParts.add("name = ?");
            params.add(dto.name());
        }
        if (dto.description() != null) {
            setParts.add("description = ?");
            params.add(dto.description());
        }
        if (dto.location() != null) {
            setParts.add("location = ?");
            params.add(dto.location());
        }
        if (dto.phoneNumber() != null) {
            setParts.add("phone_number = ?");
            params.add(dto.phoneNumber());
        }

        setParts.add("updated_at = CURRENT_TIMESTAMP");

        String sql = "UPDATE lost_items SET " + String.join(", ", setParts) + " WHERE id = ?";
        params.add(id);

        jdbcClient.sql(sql).params(params).update();

        if (dto.tags() != null) {
            jdbcClient.sql("DELETE FROM lost_item_tags WHERE lost_item_id = ?")
                    .param(id)
                    .update();

            if (!dto.tags().isEmpty()) {
                String upsertTagSql = """
                INSERT INTO tags (name) VALUES (?)
                ON CONFLICT (name) DO UPDATE SET name = EXCLUDED.name
                RETURNING id
                """;

                String linkSql = """
                INSERT INTO lost_item_tags (lost_item_id, tag_id)
                VALUES (?, ?)
                """;

                for (String tag : dto.tags()) {
                    Integer tagId = jdbcClient.sql(upsertTagSql)
                            .param(tag)
                            .query(Integer.class)
                            .single();

                    jdbcClient.sql(linkSql)
                            .param(id)
                            .param(tagId)
                            .update();
                }
            }
        }

        return getEntityById(id);
    }

    private LostItemEntity getEntityById(Integer id) {
        String sql = """
            SELECT li.id, li.name, li.description, li.phone_number,
                   li.created_at, li.updated_at, li.location
            FROM lost_items li
            WHERE li.id = :id
            """;

        LostItemEntity item = jdbcClient.sql(sql)
                .param("id", id)
                .query((rs, rowNum) -> {
                    LostItemEntity entity = new LostItemEntity(
                            rs.getString("name"),
                            rs.getString("description"),
                            new ArrayList<>(), // теги додамо нижче
                            rs.getString("location"),
                            rs.getString("phone_number")
                    );
                    entity.setId(rs.getInt("id"));
                    entity.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    entity.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                    return entity;
                })
                .single();

        List<String> tags = jdbcClient.sql("""
            SELECT t.name
            FROM tags t
            JOIN lost_item_tags lit ON t.id = lit.tag_id
            WHERE lit.lost_item_id = :id
            """)
                .param("id", id)
                .query(String.class)
                .list();
        System.out.println("Found {"+tags.size()+"} tags for item {"+id+"}");

        item.getTags().addAll(tags);
        return item;
    }
}