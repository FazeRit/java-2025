package com.example.lab2.lostItem.entity;

import com.example.lab2.common.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class TagEntity extends AbstractEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public TagEntity(String name) {
        this.name = name;
    }
}