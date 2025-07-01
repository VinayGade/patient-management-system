package com.demo.patient_management.patient_service.exceptions;

public class PatientNotFoundException extends Exception{

    public PatientNotFoundException(String message) {
        super(message);
    }
}
