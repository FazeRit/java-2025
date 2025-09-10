package com.example.lab2.lostItem.exceptions;

import java.util.UUID;

public class LostItemNotFoundException extends Exception{
    public LostItemNotFoundException(UUID id){
        super("Lost item with id " + id + " not found");
    }

    public LostItemNotFoundException(String name){
        super("Lost item with name " + name + " not found");
    }
}
