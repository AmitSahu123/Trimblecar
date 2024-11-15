package com.CRMS.eception;

public class LeaseLimitExceededException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LeaseLimitExceededException(String message) {
        super(message);
    }
}
