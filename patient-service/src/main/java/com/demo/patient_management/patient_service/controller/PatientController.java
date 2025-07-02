package com.demo.patient_management.patient_service.controller;

import com.demo.patient_management.patient_service.dto.PatientRequestDTO;
import com.demo.patient_management.patient_service.dto.PatientResponseDTO;
import com.demo.patient_management.patient_service.exceptions.PatientNotFoundException;
import com.demo.patient_management.patient_service.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService){

        this.patientService=patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){

        List<PatientResponseDTO> patients = patientService.getAll();
        return ResponseEntity.ok().body(patients);
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientRequestDTO patientRequestDTO){

        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> update(@PathVariable UUID id,
         @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO)
            throws PatientNotFoundException {

        PatientResponseDTO patientResponseDTO = patientService
                .updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){

        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
