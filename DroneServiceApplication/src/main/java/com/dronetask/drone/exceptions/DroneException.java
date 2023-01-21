package com.dronetask.drone.exceptions;

public class DroneException extends RuntimeException{
    public DroneException() {
    }

    public DroneException(String message) {
        super(message);
    }
}
