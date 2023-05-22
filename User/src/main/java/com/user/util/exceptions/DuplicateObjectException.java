package com.user.util.exceptions;

public class DuplicateObjectException extends RuntimeException {
    public DuplicateObjectException(String message) {
        super(message);
    }

    public DuplicateObjectException() {
        super();
    }
}
