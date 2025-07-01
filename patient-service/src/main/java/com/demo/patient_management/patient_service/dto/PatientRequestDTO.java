package com.demo.patient_management.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "name is required.")
    private String name;

    @NotBlank(message = "email is required.")
    @Email(message = "email should be valid.")
    private String email;

    @NotBlank(message = "name is required.")
    private String address;

    @NotBlank(message = "date of birth is required.")
    private String dateOfBirth;

    @NotBlank(message = "Registered date is required.")
    private String registeredDate;
}
