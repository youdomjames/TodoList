package com.task.util.exception;

public class ObjectNotFound extends RuntimeException {
    public ObjectNotFound(String message) {
        super(message);
    }

    public ObjectNotFound(){
        super();
    }
}
