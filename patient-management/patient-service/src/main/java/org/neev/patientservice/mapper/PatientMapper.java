package org.neev.patientservice.mapper;

import org.neev.patientservice.model.PatientRequestDTO;
import org.neev.patientservice.model.PatientResponseDTO;
import org.neev.patientservice.exception.InvalidDOBException;
import org.neev.patientservice.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class PatientMapper {
    private PatientMapper() {
    }

    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(String.valueOf(patient.getId()));
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDob(patient.getDob().toString());
        return patientResponseDTO;
    }

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
