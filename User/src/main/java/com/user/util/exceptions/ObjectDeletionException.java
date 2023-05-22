package com.user.util.exceptions;

public class ObjectDeletionException extends RuntimeException {
    public ObjectDeletionException(String message) {
    super(message);
    }
    public ObjectDeletionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectDeletionException(){
        super();
    }
}
