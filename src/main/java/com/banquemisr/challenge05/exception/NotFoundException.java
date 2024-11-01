package com.banquemisr.challenge05.exception;

public class NotFoundException extends RuntimeException {
    String message;
    public NotFoundException(String message) {
        this.message = message;
    }
}
