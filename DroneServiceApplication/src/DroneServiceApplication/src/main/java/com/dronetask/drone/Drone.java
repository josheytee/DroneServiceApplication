package com.dronetask.drone;

import com.dronetask.medication.Medication;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name ="drones")
public class Drone {
    @Id
    @SequenceGenerator(name="drone_sequence", sequenceName = "drone_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drone_sequence")
    private Long id;
    @Column(name="serial_number", length = 100)
    private String serialNumber;

    @Column(name="model")
    @Enumerated(value = EnumType.STRING)
    private DroneModel model;

    @Column(name="weight_limit")
    @DecimalMax(value = "500", message =" Drone cannot carry more than {value} grams")
    private Double weightLimit; // Maximum weight the drone can carry

    @Column(name="battery_level")
    @Max(value=100,message="Battery life cannot be more than 100%")
    private Integer batteryLevel; // Battery capacity measured in percentage %

    @Column(name = "drone_state")
    @Enumerated(value = EnumType.STRING)
    private DroneState droneState; // Current state of the drone

    @OneToMany(mappedBy = "drone")
    @JsonManagedReference
    private List<Medication> medications;

    @Override
    public String toString() {
        return "Drone{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", batteryLevel=" + batteryLevel +
                ", droneState=" + droneState +
                ", medications=" + Arrays.toString(new List[]{medications}) +
                '}';
    }

    public Drone() {
    }

    public Drone(Long id, String serialNumber, DroneModel model, Double weightLimit, Integer batteryLevel, DroneState droneState, List<Medication> medications) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryLevel = batteryLevel;
        this.droneState = droneState;
        this.medications = medications;
    }

    public Drone(String serialNumber, DroneModel model, Double weightLimit, Integer batteryLevel, DroneState droneState) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryLevel = batteryLevel;
        this.droneState = droneState;
//        this.currentWeight = currentWeight;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public DroneModel getDroneModel() {
        return model;
    }

    public void setDroneModel(DroneModel model) {
        this.model = model;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Integer getBatteryLevel() {
        return batteryLevel;
    }

    public void setbatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public DroneState getDroneState() {
        return droneState;
    }

    public void setDroneState(DroneState droneState) {
        this.droneState = droneState;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
