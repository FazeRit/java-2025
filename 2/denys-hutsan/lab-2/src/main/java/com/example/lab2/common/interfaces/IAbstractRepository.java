package com.example.lab2.common.interfaces;

import com.example.lab2.common.entity.AbstractEntity;

public interface IAbstractRepository <TItem extends AbstractEntity>{
    public void create(TItem t);
}