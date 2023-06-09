package com.user.controller;

import com.user.util.enums.CustomStatus;
import com.user.util.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {DuplicateObjectException.class})
    protected ResponseEntity<Object> handleConflictException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        log.debug(Arrays.toString(ex.getStackTrace()));
//        log.debug(Objects.requireNonNull(request.getUserPrincipal()).getName() + "tried to add a new duplicate object");
        return new ResponseEntity<>("ERROR: " + errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        log.error(Arrays.toString(ex.getStackTrace()));
//        log.debug(Objects.requireNonNull(request.getUserPrincipal()).getName() + "tried to get non-existing object");
        return new ResponseEntity<>("ERROR: " + errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ObjectDeletionException.class, ObjectCreationException.class})
    public ResponseEntity<String> handleInternalServerException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        log.error(Arrays.toString(ex.getStackTrace()));
//        log.debug(Objects.requireNonNull(request.getUserPrincipal()).getName() + "tried to delete object");
        return new ResponseEntity<>("ERROR: " + errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ObjectUpdateException.class})
    public ResponseEntity<String> handleBadRequestException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        log.error(Arrays.toString(ex.getStackTrace()));
//        log.debug(Objects.requireNonNull(request.getUserPrincipal()).getName() + "tried to update object");
        return new ResponseEntity<>("ERROR: " + errorMessage, HttpStatus.BAD_REQUEST);
    }

}
