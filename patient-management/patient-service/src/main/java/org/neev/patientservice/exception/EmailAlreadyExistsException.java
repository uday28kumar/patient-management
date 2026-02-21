package org.neev.patientservice.exception;

/**
 * Custom exception thrown when an attempt is made to create a patient with an email address that already exists in the system.
 * This exception extends RuntimeException, allowing it to be thrown without being declared in method signatures. It can be
 * caught and handled by a global exception handler to return an appropriate HTTP response to the client.
 */
public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
