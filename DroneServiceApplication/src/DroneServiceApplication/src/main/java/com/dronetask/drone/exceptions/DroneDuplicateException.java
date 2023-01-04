package com.dronetask.drone.exceptions;

public class DroneDuplicateException extends DroneException {
    public DroneDuplicateException(String serialNumber) {
        super("Cannot duplicate drone with serial number" + serialNumber);
    }
}
