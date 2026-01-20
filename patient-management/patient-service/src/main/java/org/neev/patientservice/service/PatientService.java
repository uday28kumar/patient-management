package org.neev.patientservice.service;

import org.neev.billing.grpc.BillingResponse;
import org.neev.patientservice.dto.PatientRequestDTO;
import org.neev.patientservice.dto.PatientResponseDTO;
import org.neev.patientservice.exception.EmailAlreadyExistsException;
import org.neev.patientservice.exception.InvalidDOBException;
import org.neev.patientservice.exception.PatientNotFoundException;
import org.neev.patientservice.grpc.BillingServiceGrpcClient;
import org.neev.patientservice.mapper.PatientMapper;
import org.neev.patientservice.model.Patient;
import org.neev.patientservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email (" + patientRequestDTO.getEmail() + ") already exists.");
        }
        Patient patient = PatientMapper.toModel(patientRequestDTO);
        patient.setRegistrationTime(LocalDateTime.now());
        Patient savedPatient = patientRepository.save(patient);
        BillingResponse billingResponse = billingServiceGrpcClient.createBilling("fake id", "fake name");
        log.info("Billing Response = {}", billingResponse);
        return PatientMapper.toDTO(savedPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient with this email (" + patientRequestDTO.getEmail() + ") already exists.");
        }
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        try {
            patient.setDob(LocalDate.parse(patientRequestDTO.getDob()));
        } catch (DateTimeParseException exception) {
            throw new InvalidDOBException("Invalid dob: " + exception.getMessage());
        }
        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}