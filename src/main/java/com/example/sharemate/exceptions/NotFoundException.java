package com.example.sharemate.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String message){
        super(message);
    }
}
