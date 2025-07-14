package com.neev.patient_service.service;

import com.neev.patient_service.dto.PatientRequestDTO;
import com.neev.patient_service.dto.PatientResponseDTO;
import com.neev.patient_service.exception.EmailAlreayExistsException;
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

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreayExistsException("A patient with this email (" + patientRequestDTO.getEmail() + ") already exists.");
        }
        Patient patient = PatientMapper.toModel(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(savedPatient);
    }
}