package com.kamyczki.commons.error;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public enum ErrorCodes {
    RESOURCE_ALREADY_EXISTS("Resource %s with this %s already exists", BAD_REQUEST),
    RESOURCE_NOT_FOUND("Resource %s with %s = %s not found", NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    ErrorCodes(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public void throwWithObjectAndField(String entity, String field) {
        throw new ErrorException(this.name(), this.message.formatted(entity, field), field, this.status);
    }

    public void throwWithObjectAndFieldAndValue(String entity, String field, String value) {
        throw new ErrorException(this.name(), this.message.formatted(entity, field, value), field, this.status);
    }
}
