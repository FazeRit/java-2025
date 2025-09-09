package com.example.lab2.common.interfaces;

import com.example.lab2.common.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface IAbstractRepository <TItem extends AbstractEntity, TId>{
    public void create(TItem t);
    public List<TItem> getEntities(TItem... t);
    public Optional<TItem> getEntityById(TId id);
}