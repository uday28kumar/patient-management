package com.neev.patient_service.service;

import com.neev.patient_service.dto.PatientResponse;
import com.neev.patient_service.mapper.PatientMapper;
import com.neev.patient_service.model.Patient;
import com.neev.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponse> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }
}
