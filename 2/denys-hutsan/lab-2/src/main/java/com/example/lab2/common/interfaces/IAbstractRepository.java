package com.example.lab2.common.interfaces;

import com.example.lab2.common.entity.AbstractEntity;

import java.util.List;

public interface IAbstractRepository <TItem extends AbstractEntity>{
    public void create(TItem t);
    public List<TItem> getEntities(TItem... t);
}