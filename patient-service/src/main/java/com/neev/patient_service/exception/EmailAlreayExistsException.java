package com.neev.patient_service.exception;

public class EmailAlreayExistsException extends RuntimeException {
    public EmailAlreayExistsException(String message) {
        super(message);
    }
}
