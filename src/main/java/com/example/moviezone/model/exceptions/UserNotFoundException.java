package com.example.moviezone.model.exceptions;


public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("user not found");
    }
}
