package com.dronetask.drone.requests;

import com.dronetask.medication.Medication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class LoadDroneRequest {
    @NotBlank
    @NotNull
    private String serialNumber;

    private List<Medication> medications;
    private List<String> medicationCodes;

    public LoadDroneRequest() {
        medications = new ArrayList<>();
        medicationCodes = new ArrayList<>();
    }

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

    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public void setMedicationCodes(List<String> medicationCodes) {
        this.medicationCodes = medicationCodes;
    }
}
