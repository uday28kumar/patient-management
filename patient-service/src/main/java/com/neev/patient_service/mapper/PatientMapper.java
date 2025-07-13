package com.neev.patient_service.mapper;

import com.neev.patient_service.dto.PatientResponse;
import com.neev.patient_service.model.Patient;

public class PatientMapper {
    public static PatientResponse toDTO(Patient patient) {
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setId(String.valueOf(patient.getId()));
        patientResponse.setName(patient.getName());
        patientResponse.setEmail(patient.getEmail());
        patientResponse.setAddress(patient.getAddress());
        patientResponse.setDob(patient.getDob().toString());
        return patientResponse;
    }
}
