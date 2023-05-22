package com.user.util.exceptions;

public class ObjectUpdateException extends RuntimeException {
    public ObjectUpdateException(String message) {
        super(message);
    }
    public ObjectUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectUpdateException(Throwable cause) {
        super(cause);
    }

    public ObjectUpdateException(){
        super();
    }
}
