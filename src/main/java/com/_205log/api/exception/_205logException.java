package com._205log.api.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class _205logException extends RuntimeException{

    public final Map<String,String> validation = new HashMap<>();
    public _205logException(String message) {
        super(message);
    }

    public _205logException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
