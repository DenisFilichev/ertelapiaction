package com.example.ertelapiaction.exceptions;

public class ActionNotFoundException extends RuntimeException{

    public ActionNotFoundException(String message) {
        super(message);
    }
}
