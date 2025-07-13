package com.neev.patient_service.controller;

import com.neev.patient_service.dto.PatientResponse;
import com.neev.patient_service.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getPatients() {
        List<PatientResponse> response = patientService.getPatients();
        return ResponseEntity.ok().body(response);
    }
}
