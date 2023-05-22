package com.task.util.exception;

public class ObjectDeletionException extends RuntimeException {

    public ObjectDeletionException(String message) {
        super(message);
    }

    public ObjectDeletionException() {
        super();
    }
}
