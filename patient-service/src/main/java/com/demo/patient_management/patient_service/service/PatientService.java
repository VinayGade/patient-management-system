package com.demo.patient_management.patient_service.service;

import com.demo.patient_management.patient_service.dto.PatientRequestDTO;
import com.demo.patient_management.patient_service.exceptions.EmailAlreadyExistsException;
import com.demo.patient_management.patient_service.mapper.PatientMapper;
import com.demo.patient_management.patient_service.model.Patient;
import com.demo.patient_management.patient_service.repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

import com.demo.patient_management.patient_service.dto.PatientResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAll(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs = patients.stream()
                .map(PatientMapper::toDTO)
                .toList();
        return patientResponseDTOs;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){

        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Patient with this email already exists: "+
                    patientRequestDTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toEntity(patientRequestDTO));
        return PatientMapper.toDTO(patient);
    }
}
