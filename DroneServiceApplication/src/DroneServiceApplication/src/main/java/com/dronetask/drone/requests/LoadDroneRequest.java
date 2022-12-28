package com.dronetask.drone.requests;

import com.dronetask.medication.Medication;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class LoadDroneRequest {
    @NotBlank
    @NotNull
    private String serialNumber;

//    @JsonProperty
    private List<Medication> medications;
    private List<String> medicationCodes;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<String> getMedicationCodes() {
        return medicationCodes;
    }

    public void setMedicationCodes(List<String> medicationCodes) {
        this.medicationCodes = medicationCodes;
    }
}
