package com.notification.util.exceptions;

public class PersistenceException extends RuntimeException {
    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException() {
        super();
    }
}
