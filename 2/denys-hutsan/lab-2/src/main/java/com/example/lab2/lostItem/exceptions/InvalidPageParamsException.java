package com.example.lab2.lostItem.exceptions;

public class InvalidPageParamsException extends Exception{
    public InvalidPageParamsException(){
        super("Page number and number of items per page must be greater than or equal to 1.");
    }
}
