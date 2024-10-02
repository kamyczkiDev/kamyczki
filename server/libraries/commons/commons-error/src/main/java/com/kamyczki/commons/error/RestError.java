package com.kamyczki.commons.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RestError {
    private final String code;
    private final String message;
    private final String field;

    public RestError(String code, String message) {
        this.code = code;
        this.message = message;
        this.field = null;
    }
}
