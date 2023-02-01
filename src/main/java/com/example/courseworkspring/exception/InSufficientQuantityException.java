package com.example.courseworkspring.exception;

public class InSufficientQuantityException extends RuntimeException {
    public InSufficientQuantityException(String message) {
        super(message);
    }
}
