package com.notification.controller;

import com.notification.util.exceptions.ObjectNotFoundException;
import com.notification.util.exceptions.PersistenceException;
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

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        log.error(Arrays.toString(ex.getStackTrace()));
//        log.debug(Objects.requireNonNull(request.getUserPrincipal()).getName() + "tried to get non-existing object");
        return new ResponseEntity<>("ERROR: " + errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {PersistenceException.class, IllegalStateException.class})
    public ResponseEntity<String> handleInternalServerException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        log.error(Arrays.toString(ex.getStackTrace()));
//        log.debug(Objects.requireNonNull(request.getUserPrincipal()).getName() + "tried to delete object");
        return new ResponseEntity<>("ERROR: " + errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<String> handleBadRequestException(RuntimeException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        log.error(errorMessage);
        log.error(Arrays.toString(ex.getStackTrace()));
//        log.debug(Objects.requireNonNull(request.getUserPrincipal()).getName() + "tried to update object");
        return new ResponseEntity<>("ERROR: " + errorMessage, HttpStatus.BAD_REQUEST);
    }

}
