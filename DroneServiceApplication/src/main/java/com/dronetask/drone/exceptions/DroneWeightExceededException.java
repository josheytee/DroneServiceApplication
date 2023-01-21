package com.dronetask.drone.exceptions;

import com.dronetask.drone.Drone;

public class DroneWeightExceededException extends DroneException {
    public DroneWeightExceededException(Drone drone) {
        super("Drone Weight Limit of "+drone.getWeightLimit()+" is Exceeded ");
    }
}
