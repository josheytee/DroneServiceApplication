package com.dronetask.drone;

import com.dronetask.drone.requests.LoadDroneRequest;
import com.dronetask.drone.requests.RegisterDroneRequest;
import com.dronetask.drone.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "api/v1/drone")
public class DroneController {
    private final DroneService droneService;

    @Autowired
    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    /**
     * stores drones in db
     * confirms serial number is not duplicated
     * default drone state is idle
     *
     * @param droneRequest
     * @return
     */
    @PostMapping(path = "/register")
    public ResponseEntity<RegisterDroneResponse> registerDrone(@Valid @NotNull @RequestBody RegisterDroneRequest droneRequest) {
        RegisterDroneResponse newDrone = droneService.registerDrone(droneRequest);
        return new ResponseEntity<RegisterDroneResponse>(newDrone, HttpStatus.CREATED);
    }

    /**
     * Confirms Drone batery health is more than 25 and medication weight is less than drone weight limit
     *
     * @param droneRequest
     * @return
     * @throws com.dronetask.drone.exceptions.DroneWeightExceededException, {@link com.dronetask.drone.exceptions.DroneLowBatteryException}
     */
    @PostMapping(path = "/load")
    public ResponseEntity<LoadDroneResponse> loadDrone(@Valid @NotNull @RequestBody LoadDroneRequest droneRequest) {
        LoadDroneResponse loadDroneResponse = droneService.loadDrone(droneRequest);
        return new ResponseEntity<LoadDroneResponse>(loadDroneResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/getDroneMedications")
    public ResponseEntity<DroneResponse> getLoadedMedications(@Valid @NotNull @RequestParam(name = "serial_number", required = true) String serialNumber) {
        DroneResponse loadDroneResponse = droneService.getLoadedMedications(serialNumber);

        return new ResponseEntity<DroneResponse>(loadDroneResponse, HttpStatus.OK);

    }

    @GetMapping(path = "/checkAvailableDrones")
    public ResponseEntity<DroneResponse> checkAvailableDrone() {
        DroneResponse loadDroneResponse = droneService.getAvailableDrones();

        return new ResponseEntity<DroneResponse>(loadDroneResponse, HttpStatus.OK);

    }

    @GetMapping(path = "/getDroneBatteryLevel")
    public ResponseEntity<DroneResponse> checkDronesBatteryLevel(@Valid @NotNull @RequestParam(name = "serial_number", required = true) String serialNumber) {
        DroneResponse loadDroneResponse = droneService.getDroneBatteryLevel(serialNumber);

        return new ResponseEntity<DroneResponse>(loadDroneResponse, HttpStatus.OK);
    }


}
