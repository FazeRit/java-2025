package com.example.lab2.common.interfaces;

import com.example.lab2.common.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T extends AbstractEntity, ID>{
    void create(T t);
    List<T> getEntities(int page, int itemsPerPage);
    Optional<T> getEntityById(ID id);
}