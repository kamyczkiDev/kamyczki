package com.kamyczki.gateway.exception;

import com.kamyczki.commons.error.ErrorException;
import com.kamyczki.commons.error.RestError;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorException> handleFeignException(FeignException ex) {
        var errorResponse = new ErrorException(
                "Microservice Error: " + ex.getMessage(),
                "MICROSERVICE_ERROR",
                HttpStatus.BAD_GATEWAY
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorException> handleHttpClientError(HttpClientErrorException ex) {
        var errorResponse = new ErrorException(
                "Client Error: " + ex.getMessage(),
                "HTTP_CLIENT_ERROR",
                ex.getStatusCode()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }


    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<RestError> handleErrorException(ErrorException ex, WebRequest request) {
        // Build a response entity with the status code and error message
        RestError errorResponse = new RestError(ex.getCode(), ex.getMessage(), null);
        return new ResponseEntity<>(errorResponse, ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleErrorException(Exception ex) {
        return new ResponseEntity<>(ex,INTERNAL_SERVER_ERROR);
    }


}