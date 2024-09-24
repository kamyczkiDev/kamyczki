package com.kamyczki.auth.shared;

import com.kamyczki.commons.error.ErrorException;
import com.kamyczki.commons.error.RestError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<RestError> handleErrorException(ErrorException ex, WebRequest request) {
        RestError errorResponse = new RestError(ex.getCode(), ex.getMessage(), ex.getField());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }
}