package com.example.lab2.lostItem.exceptions;

public class LostItemNotFoundException extends Exception{
    public LostItemNotFoundException(Integer id){
        super("Lost item with id " + id + " not found");
    }
    public LostItemNotFoundException(String message) {
        super(message);
    }
}
