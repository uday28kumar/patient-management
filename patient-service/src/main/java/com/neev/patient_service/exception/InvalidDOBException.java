package com.neev.patient_service.exception;

public class InvalidDOBException extends RuntimeException{
    public InvalidDOBException(String message) {
        super(message);
    }
}
