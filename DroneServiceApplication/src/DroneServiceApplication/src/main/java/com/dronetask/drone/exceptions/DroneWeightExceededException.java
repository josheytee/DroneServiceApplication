package com.dronetask.drone.exceptions;

public class DroneWeightExceededException extends RuntimeException {
    public DroneWeightExceededException() {
        super("Drone Weight Limit Exceeded");
    }
}
