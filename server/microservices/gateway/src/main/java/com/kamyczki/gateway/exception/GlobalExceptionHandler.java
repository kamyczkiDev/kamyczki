package com.kamyczki.gateway.exception;

import com.kamyczki.commons.error.ErrorException;
import com.kamyczki.commons.error.RestError;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@SuppressWarnings("unused")
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<RestError> handleFeignException(FeignException ex) {
        var errorResponse = new RestError(
                "FEIGN_CLIENT_ERROR",
                "Feign Error: " + ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, BAD_GATEWAY);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<RestError> handleHttpClientError(HttpClientErrorException ex) {
        var errorResponse = new RestError(
                "HTTP_CLIENT_ERROR",
                "Client Error: " + ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<RestError> handleErrorException(ErrorException ex, WebRequest request) {
        RestError errorResponse = new RestError(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestError> handleErrorException(Exception ex) {
        RestError errorResponse = new RestError("UNEXPECTED_ERROR", ex.getMessage());
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }


}