package com.example.lab2.common.interfaces;

import com.example.lab2.common.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T extends AbstractEntity, ID>{
    public void create(T t);
    public List<T> getEntities(T... t);
    public Optional<T> getEntityById(ID id);
}