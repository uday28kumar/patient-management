package org.neev.patientservice.controller;

import org.neev.patientservice.model.PatientRequestDTO;
import org.neev.patientservice.model.PatientResponseDTO;
import org.neev.patientservice.service.PatientService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing patient records. This controller provides endpoints for creating, retrieving,
 * updating, and deleting patient information. It interacts with the PatientService to perform business logic
 * and data access operations.
 */
@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Retrieves a list of all patients. This endpoint returns a collection of patient records in the form of
     * PatientResponseDTO objects.
     *
     * @return a ResponseEntity containing the list of patients
     */
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> response = patientService.getPatients();

        return ResponseEntity.ok().body(response);
    }

    /**
     * Creates a new patient record. This endpoint accepts a PatientRequestDTO object in the request body, which contains
     * the necessary information for creating a new patient. The method validates the input data and returns a PatientResponseDTO object representing the newly created patient.
     * @param patientRequestDTO the request body containing the information for the new patient, validated against the Default validation group
     * @return a ResponseEntity containing the PatientResponseDTO of the newly created patient
     */
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated(Default.class) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    /**
     * Updates an existing patient record. This endpoint accepts a PatientRequestDTO object in the request body, which contains
     * the updated information for the patient. The method validates the input data and returns a PatientResponseDTO object representing the updated patient.
     *
     * @param id                the UUID of the patient to be updated, extracted from the path variable
     * @param patientRequestDTO the request body containing the updated information for the patient, validated against the Default validation group
     * @return a ResponseEntity containing the PatientResponseDTO of the updated patient
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable("id") UUID id, @Validated(Default.class) @RequestBody PatientRequestDTO patientRequestDTO){
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);

        return ResponseEntity.ok().body(patientResponseDTO);
    }

    /**
     * Deletes an existing patient record. This endpoint accepts the UUID of the patient to be deleted as a path variable.
     * The method performs the deletion operation and returns a ResponseEntity with no content if the deletion is successful.
     *
     * @param id the UUID of the patient to be deleted, extracted from the path variable
     * @return a ResponseEntity with no content if the deletion is successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") UUID id){
        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }
}
