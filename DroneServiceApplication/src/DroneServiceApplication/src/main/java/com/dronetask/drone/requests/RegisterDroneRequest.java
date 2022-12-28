package com.dronetask.drone.requests;

import com.dronetask.drone.Drone;
import com.dronetask.drone.DroneModel;
import com.dronetask.drone.DroneState;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class RegisterDroneRequest {


    @NotBlank
    @NotNull
    private String serialNumber;

    @NotBlank
    @NotNull
    private String model;

    @NotNull
    private Double weightLimit;

    @NotNull
    private Integer battery;

    @NotBlank
    @NotNull
    private String state;

    public RegisterDroneRequest(String serialNumber, String model, Double weightLimit, Integer battery, String state) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.battery = battery;
        this.state = state;
    }

    public Drone getDrone(){
        Drone drone = new Drone();
        drone.setDroneModel(DroneModel.valueOf(this.model));
        drone.setDroneState(DroneState.IDLE);
        drone.setSerialNumber(this.serialNumber);
        drone.setWeightLimit(this.weightLimit);
        drone.setbatteryLevel(this.battery);
        return drone;
    }

    @Override
    public String toString() {
        return "RegisterDroneRequest{" +
                "serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", weightLimit=" + weightLimit +
                ", battery=" + battery +
                ", state='" + state + '\'' +
                '}';
    }
}
