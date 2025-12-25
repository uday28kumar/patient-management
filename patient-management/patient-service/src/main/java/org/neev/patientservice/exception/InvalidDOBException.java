package org.neev.patientservice.exception;

public class InvalidDOBException extends RuntimeException{
    public InvalidDOBException(String message) {
        super(message);
    }
}
