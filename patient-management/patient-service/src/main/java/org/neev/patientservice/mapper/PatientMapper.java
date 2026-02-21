package org.neev.patientservice.mapper;

import org.neev.patientservice.model.PatientRequestDTO;
import org.neev.patientservice.model.PatientResponseDTO;
import org.neev.patientservice.exception.InvalidDOBException;
import org.neev.patientservice.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Mapper class responsible for converting between Patient model objects and their corresponding DTOs (Data Transfer Objects).
 * This class provides static methods to convert a Patient entity to a PatientResponseDTO for sending data to clients,
 * and to convert a PatientRequestDTO received from clients into a Patient entity for processing and storage.
 */
public class PatientMapper {
    private PatientMapper() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a Patient entity to a PatientResponseDTO. This method takes a Patient object and extracts its properties
     * to create a new PatientResponseDTO, which is suitable for sending as a response to clients.
     *
     * @param patient the Patient entity to be converted
     * @return a PatientResponseDTO containing the data from the Patient entity
     */
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(String.valueOf(patient.getId()));
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDob(patient.getDob().toString());
        return patientResponseDTO;
    }

    /**
     * Converts a PatientRequestDTO to a Patient entity. This method takes a PatientRequestDTO object, which contains
     * the data received from clients, and creates a new Patient entity that can be processed and stored in the database.
     * It also handles the parsing of the date of birth (DOB) and throws an InvalidDOBException if the DOB is not valid.
     *
     * @param patientRequestDTO the PatientRequestDTO to be converted
     * @return a Patient entity containing the data from the PatientRequestDTO
     * @throws InvalidDOBException if the DOB provided in the PatientRequestDTO is invalid
     */
    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        try {
            patient.setDob(LocalDate.parse(patientRequestDTO.getDob()));
        } catch (DateTimeParseException exception) {
            throw new InvalidDOBException("Invalid dob " + exception.getMessage());
        }
        return patient;
    }
}
