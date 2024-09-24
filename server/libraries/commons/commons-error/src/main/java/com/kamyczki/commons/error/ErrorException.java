package com.kamyczki.commons.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ErrorException extends ResponseStatusException {

    private final String code;
    private final String message;
    private final HttpStatusCode statusCode;
    private final String field;

    public ErrorException(String code, String message, HttpStatusCode status) {
        super(status);
        this.statusCode = status;
        this.message = message;
        this.code = code;
        this. field = null;
    }

    public ErrorException(String code, String message,String field, HttpStatusCode status) {
        super(status);
        this.statusCode = status;
        this.message = message;
        this.code = code;
        this. field = field;
    }

}
