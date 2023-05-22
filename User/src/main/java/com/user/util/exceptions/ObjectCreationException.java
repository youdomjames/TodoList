package com.user.util.exceptions;

public class ObjectCreationException extends RuntimeException{

    public ObjectCreationException(String message) {
        super(message);
    }

    public ObjectCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectCreationException(){
        super();
    }
}
