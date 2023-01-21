package com.dronetask.drone;

import com.dronetask.drone.requests.LoadDroneRequest;
import com.dronetask.drone.requests.RegisterDroneRequest;
import com.dronetask.drone.responses.DroneResponse;
import com.dronetask.drone.responses.LoadDroneResponse;
import com.dronetask.drone.responses.RegisterDroneResponse;

public interface DroneServiceInterface {
    public RegisterDroneResponse registerDrone(RegisterDroneRequest registerDroneRequest);

    public LoadDroneResponse loadDrone(LoadDroneRequest droneRequest);

    DroneResponse getLoadedMedications(String serialNumber);

    public DroneResponse getAvailableDrones();

    public DroneResponse getDroneBatteryLevel(String serialNumber);
}
