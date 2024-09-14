package com.kamyczki.auth.shared;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

//todo: implement error handling
public class ErrorException extends ResponseStatusException {

    public ErrorException(HttpStatusCode status, String reason) {
        super(status, reason);
    }
}
