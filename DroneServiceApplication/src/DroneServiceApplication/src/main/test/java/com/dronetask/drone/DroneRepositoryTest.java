package com.dronetask.drone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DroneRepositoryTest {

    @Autowired
    DroneRepository droneRepository;
    String serialNumber = "05,08,0C,0D,0Y,DA,DI,12,14,17";
    String invalidSerialNumber = "05,08,0C,0D,0Y,DA,DI,12,14,13";
    Drone drone;

    @BeforeEach
    void setUp() {
        //given
        drone = new Drone(serialNumber, DroneModel.Middleweight, 200.5, 50, DroneState.IDLE);
        droneRepository.save(drone);

    }

    @Test
    void CanFindDroneBySerialNumber() {

        //when
        Optional<Drone> droneBySerialNumber = droneRepository.findDroneBySerialNumber(serialNumber);
        //then
        assertSame(drone, droneBySerialNumber.get());
    }

    @Test
    void CanNotFindDroneBySerialNumber() {
        //when
        Optional<Drone> droneBySerialNumber = droneRepository.findDroneBySerialNumber(invalidSerialNumber);
        //then
        assertEquals(false, droneBySerialNumber.isPresent());
    }

    @Test
    void canUpdateDroneState() {
        int b = droneRepository.updateDroneState(serialNumber, DroneState.LOADED);
        assertEquals(1, b);
    }

    @Test
    void canNotUpdateDroneState() {
        int b = droneRepository.updateDroneState(invalidSerialNumber, DroneState.LOADED);
        assertEquals(0, b);
    }

    @Test
    void findAllByDroneState() {
        List<Drone> allByDroneState = droneRepository.findAllByDroneState(DroneState.IDLE);
        assertThat(allByDroneState).hasSize(1).contains(drone);

    }
}