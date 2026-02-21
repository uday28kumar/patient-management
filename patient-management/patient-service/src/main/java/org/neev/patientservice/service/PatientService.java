package org.neev.patientservice.service;

import org.neev.billing.grpc.BillingResponse;
import org.neev.patientservice.model.PatientRequestDTO;
import org.neev.patientservice.model.PatientResponseDTO;
import org.neev.patientservice.exception.EmailAlreadyExistsException;
import org.neev.patientservice.exception.InvalidDOBException;
import org.neev.patientservice.exception.PatientNotFoundException;
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

/**
 * Service class responsible for managing patient-related operations. This class provides methods to create, retrieve, update,
 * and delete patient records. It interacts with the PatientRepository for database operations, the BillingGrpcClient for
 * communicating with the Billing Service, and the PatientEventPublisher for publishing events related to patient creation.
 * The service also includes validation logic to ensure that patient data is consistent and that email addresses are unique.
 */
@Service
public class PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    private final BillingGrpcClient billingGrpcClient;
    private final PatientEventPublisher patientEventPublisher;

    public PatientService(PatientRepository patientRepository, BillingGrpcClient billingGrpcClient, PatientEventPublisher patientEventPublisher) {
        this.patientRepository = patientRepository;
        this.billingGrpcClient = billingGrpcClient;
        this.patientEventPublisher = patientEventPublisher;
    }

    /**
     * Retrieves a list of all patients. This method fetches all patient records from the database using the PatientRepository,
     * converts them to PatientResponseDTO objects using the PatientMapper, and returns the list of DTOs.
     *
     * @return a list of PatientResponseDTO objects representing all patients in the system
     */
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    /**
     * Creates a new patient record. This method first checks if a patient with the same email already exists in the database,
     * and if so, it throws an EmailAlreadyExistsException. If the email is unique, it converts the PatientRequestDTO to a Patient entity,
     * sets the registration time, saves the patient to the database, publishes a patient created event, and makes a gRPC call to the Billing Service.
     * Finally, it converts the saved Patient entity to a PatientResponseDTO and returns it.
     *
     * @param patientRequestDTO the request DTO containing the information for the new patient
     * @return a PatientResponseDTO representing the newly created patient
     * @throws EmailAlreadyExistsException if a patient with the same email already exists in the database
     */
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email (" + patientRequestDTO.getEmail() + ") already exists.");
        }
        Patient patient = PatientMapper.toModel(patientRequestDTO);
        patient.setRegistrationTime(LocalDateTime.now());
        Patient savedPatient = patientRepository.save(patient);
        patientEventPublisher.publishPatientCreated(savedPatient);
        BillingResponse billingResponse = billingGrpcClient.createBilling("fake id", "fake name");
        log.info("Billing Response = {}", billingResponse);
        return PatientMapper.toDTO(savedPatient);
    }

    /**
     * Updates an existing patient record. This method first retrieves the patient by ID from the database, and if the patient does not exist, it throws a PatientNotFoundException.
     * It then checks if another patient with the same email exists (excluding the current patient), and if so, it throws an EmailAlreadyExistsException.
     * If the email is unique, it updates the patient's information with the data from the PatientRequestDTO, saves the updated patient to the database,
     * and returns a PatientResponseDTO representing the updated patient.
     *
     * @param id                the UUID of the patient to be updated
     * @param patientRequestDTO the request DTO containing the updated information for the patient
     * @return a PatientResponseDTO representing the updated patient
     * @throws PatientNotFoundException    if no patient with the given ID exists in the database
     * @throws EmailAlreadyExistsException if another patient with the same email already exists in the database
     */
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

    /**
     * Deletes an existing patient record. This method deletes the patient with the given ID from the database using the PatientRepository.
     *
     * @param id the UUID of the patient to be deleted
     */
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}