package org.neev.patientservice.exception;

/**
 * Custom exception thrown when a patient with a specified ID is not found in the system. This exception extends RuntimeException,
 * allowing it to be thrown without being declared in method signatures. It can be caught and handled by a global exception handler
 * to return an appropriate HTTP response to the client, indicating that the requested patient was not found.
 */
public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String message) {
        super(message);
    }
}
