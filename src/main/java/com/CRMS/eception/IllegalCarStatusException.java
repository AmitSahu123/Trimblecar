package com.CRMS.eception;

public class IllegalCarStatusException extends RuntimeException {
    public IllegalCarStatusException(String message) {
        super(message);
    }
}

