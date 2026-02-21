package org.neev.patientservice.exception;

/**
 * Custom exception thrown when an invalid date of birth (DOB) is provided for a patient. This exception extends RuntimeException,
 * allowing it to be thrown without being declared in method signatures. It can be caught and handled by a global exception handler
 * to return an appropriate HTTP response to the client, indicating that the provided DOB is invalid.
 */
public class InvalidDOBException extends RuntimeException{
    public InvalidDOBException(String message) {
        super(message);
    }
}
