package com.dronetask.drone;

import com.dronetask.drone.exceptions.DroneDuplicateException;
import com.dronetask.drone.exceptions.DroneLowBatteryException;
import com.dronetask.drone.exceptions.DroneNotFoundException;
import com.dronetask.drone.exceptions.DroneWeightExceededException;
import com.dronetask.drone.requests.LoadDroneRequest;
import com.dronetask.drone.requests.RegisterDroneRequest;
import com.dronetask.drone.responses.DroneResponse;
import com.dronetask.drone.responses.LoadDroneResponse;
import com.dronetask.drone.responses.RegisterDroneResponse;
import com.dronetask.medication.Medication;
import com.dronetask.medication.MedicationNotFoundException;
import com.dronetask.medication.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService implements DroneServiceInterface {

    DroneRepository droneRepository;
    MedicationRepository medicationRepository;

    public DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public RegisterDroneResponse registerDrone(RegisterDroneRequest registerDroneRequest) {
        Drone newDrone = registerDroneRequest.getDrone();
        Optional<Drone> existingDrone = droneRepository.findDroneBySerialNumber(newDrone.getSerialNumber());
        if (existingDrone.isPresent()) {
            throw new DroneDuplicateException( newDrone.getSerialNumber() );
        }
        droneRepository.save(newDrone);

        RegisterDroneResponse droneResponse = new RegisterDroneResponse();
        droneResponse.setMessage("Drone Successfully Registered1");
        droneResponse.setStatus(201);
        droneResponse.setData(newDrone);
        return droneResponse;
    }

    @Transactional
    @Override
    public LoadDroneResponse loadDrone(LoadDroneRequest droneRequest) {
        Drone drone = droneRepository.findDroneBySerialNumber(droneRequest.getSerialNumber())
                .orElseThrow(() -> new DroneNotFoundException("Drone with Serial Number: " + droneRequest.getSerialNumber() + " does not exist"));

        if (drone.getBatteryLevel() < 25) throw new DroneLowBatteryException();
        if ((droneRequest.getMedicationCodes() == null || droneRequest.getMedicationCodes().isEmpty())
                && (droneRequest.getMedications() == null || droneRequest.getMedications().isEmpty()))
            throw new RuntimeException("Medications are required to load a drone");
        if (drone.getDroneState().ordinal() > DroneState.LOADED.ordinal()){
            throw new RuntimeException("Drone is currently not available for Loading");
        }
        drone.setDroneState(DroneState.LOADING);
        droneRepository.updateDroneState(droneRequest.getSerialNumber(), DroneState.LOADING);

        List<Medication> medications = new ArrayList<>();
        double weightTotal = 0.0;
        if (!droneRequest.getMedications().isEmpty()) {
            for (Medication medication : droneRequest.getMedications()) {
                medication.setDrone(drone);
                weightTotal += medication.getWeight();
                medications.add(medication);
            }
        }
        if (droneRequest.getMedicationCodes() != null) {
            if (!droneRequest.getMedicationCodes().isEmpty()) {
                for (String medicationCode : droneRequest.getMedicationCodes()) {
                    Medication medicationByCode = medicationRepository.findMedicationByCode(medicationCode)
                            .orElseThrow(() -> new MedicationNotFoundException(medicationCode));
                    medicationByCode.setDrone(drone);
                    weightTotal += medicationByCode.getWeight();
                    medications.add(medicationByCode);
                }
            }
        }
        if (weightTotal > drone.getWeightLimit()) throw new DroneWeightExceededException(drone);

        drone.setDroneState(DroneState.LOADED);
        droneRepository.updateDroneState(droneRequest.getSerialNumber(), DroneState.LOADED);
        medicationRepository.saveAll(medications);
        drone.setMedications(medications);


        LoadDroneResponse loadDroneResponse = new LoadDroneResponse();
        loadDroneResponse.setStatus(200);
        loadDroneResponse.setMessage("Drone with Serial Number:" + droneRequest.getSerialNumber() + " loaded Successfully");
        loadDroneResponse.setData(drone);
        return loadDroneResponse;
    }


    @Override
    public DroneResponse getLoadedMedications(String serialNumber) {
        Drone droneBySerialNumber = droneRepository.findDroneBySerialNumber(serialNumber)
                .orElseThrow(() -> new DroneNotFoundException(serialNumber));
        return new DroneResponse(200, "Medication Items for drone Loaded successfully", droneBySerialNumber.getMedications());
    }

    @Override
    public DroneResponse getAvailableDrones() {

        List<Drone> droneBySerialNumber = droneRepository.findAllByDroneState(DroneState.IDLE);
        return new DroneResponse(200, "Drones with IDLE status loaded successfully", droneBySerialNumber.toArray());

    }

    @Override
    public DroneResponse getDroneBatteryLevel(String serialNumber) {
        Drone droneBySerialNumber = droneRepository.findDroneBySerialNumber(serialNumber)
                .orElseThrow(() -> new DroneNotFoundException(serialNumber));
        return new DroneResponse(200, "Battery level for drone:" + serialNumber + " Loaded successfully in %",
                droneBySerialNumber.getBatteryLevel());
    }
}
