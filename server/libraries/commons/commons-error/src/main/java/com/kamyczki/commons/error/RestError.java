package com.kamyczki.commons.error;

import lombok.Data;

@Data
public class RestError {
    private final String code;
    private final String message;
    private final String field;
}
