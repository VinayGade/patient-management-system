package com.demo.patient_management.patient_service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PatientResponseDTO {

    private String id;

    private String name;

    private String email;

    private String address;

    private String dateOfBirth;
}
