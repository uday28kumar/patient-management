package org.neev.patientservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the Patient Service application. This class uses the @ControllerAdvice annotation to
 * handle exceptions thrown by controller methods across the entire application. It defines specific handlers for
 * various exceptions, such as validation errors and custom exceptions related to patient management, and returns
 * appropriate HTTP responses with error messages.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String MESSAGE = "message";

    /**
     * Handles validation exceptions that occur when method arguments fail validation checks. This method extracts
     * the field errors from the exception and constructs a response containing the error messages for each invalid field.
     *
     * @param exception the MethodArgumentNotValidException thrown when validation fails
     * @return a ResponseEntity containing a map of field names to error messages, with a bad request status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles the EmailAlreadyExistsException, which is thrown when an attempt is made to create a patient with an email address that already exists in the system.
     * This method constructs a response containing a message indicating that the email address is already in use.
     *
     * @param exception the EmailAlreadyExistsException thrown when a duplicate email is detected
     * @return a ResponseEntity containing a map with a message about the duplicate email, with a bad request status
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put(MESSAGE, "Email address already exists");

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles the InvalidDOBException, which is thrown when an invalid date of birth is provided for a patient. This method constructs a response containing a message indicating that the DOB should be valid.
     *
     * @param exception the InvalidDOBException thrown when an invalid date of birth is detected
     * @return a ResponseEntity containing a map with a message about the invalid DOB, with a bad request status
     */
    @ExceptionHandler(InvalidDOBException.class)
    public ResponseEntity<Map<String, String>> handleInvalidDOBException(InvalidDOBException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put(MESSAGE, "DOB should be valid");

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles the PatientNotFoundException, which is thrown when a requested patient record cannot be found in the system. This method constructs a response containing a message indicating that the patient was not found.
     *
     * @param exception the PatientNotFoundException thrown when a patient record is not found
     * @return a ResponseEntity containing a map with a message about the missing patient, with a bad request status
     */
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotFoundException(PatientNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put(MESSAGE, "Patient not found");

        return ResponseEntity.badRequest().body(errors);
    }
}
