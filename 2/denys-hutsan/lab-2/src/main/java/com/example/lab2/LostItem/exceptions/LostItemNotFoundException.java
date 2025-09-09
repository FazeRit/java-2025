package com.example.lab2.LostItem.exceptions;

import java.util.UUID;

public class LostItemNotFoundException extends Exception{
    public LostItemNotFoundException(UUID id){
        super("Lost item with id " + id + " not found");
    }
}
