package org.neev.patientservice.repository;

import org.neev.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Patient entities. This interface extends JpaRepository, providing CRUD operations
 * and additional query methods for the Patient entity. It includes methods to check for the existence of a patient
 * by email, as well as to check for the existence of a patient with a specific email while excluding a particular ID.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    /**
     * Checks if a patient with the specified email address exists in the database.
     *
     * @param email the email address to check for existence
     * @return true if a patient with the specified email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Checks if a patient with the specified email address exists in the database, excluding a patient with the specified ID.
     * This is useful for validating email uniqueness when updating a patient's information.
     *
     * @param email the email address to check for existence
     * @param id the ID of the patient to exclude from the check
     * @return true if a patient with the specified email exists (excluding the specified ID), false otherwise
     */
    boolean existsByEmailAndIdNot(String email, UUID id);
}
