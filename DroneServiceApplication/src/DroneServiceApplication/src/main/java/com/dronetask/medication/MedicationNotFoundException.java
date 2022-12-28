package com.dronetask.medication;

public class MedicationNotFoundException extends RuntimeException {
    public MedicationNotFoundException(String code) {
        super("Medication with code: " +code+" Not found");
    }
}
