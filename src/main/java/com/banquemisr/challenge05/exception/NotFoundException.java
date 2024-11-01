package com.example.banquemisr.exception;

public class NotFoundException extends RuntimeException {
    String message;
    public NotFoundException(String message) {
        this.message = message;
    }
}
