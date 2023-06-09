package com.dronetask.drone;

import com.dronetask.drone.requests.RegisterDroneRequest;
import com.dronetask.drone.responses.RegisterDroneResponse;
import com.dronetask.medication.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DroneServiceTest {

    @Mock
    DroneRepository droneRepository;
    @Mock
    MedicationRepository medicationRepository;
    DroneService underTest;
    RegisterDroneRequest goodDroneReq;

    @BeforeEach
    void setUp() {
        underTest = new DroneService(droneRepository, medicationRepository);
        goodDroneReq =
                new RegisterDroneRequest("05,08,0C,0D,0Y,DA,DI,12,14,17",
                        DroneModel.Lightweight.toString(), 300.4, 70, DroneState.IDLE.toString());
    }

    @Test
    void canRegisterDrone() {
        //given

        RegisterDroneResponse registerDroneResponse = underTest.registerDrone(goodDroneReq);

        assertThat(registerDroneResponse).hasFieldOrPropertyWithValue("status", 201)
                .hasFieldOrProperty("message")
                .hasFieldOrPropertyWithValue("data", registerDroneResponse.getData());

    }

    @Test
    void canLoadDrone() {

    }

    @Test
    void getLoadedMedications() {
    }

    @Test
    void getAvailableDrones() {
    }

    @Test
    void getDroneBatteryLevel() {
    }
}