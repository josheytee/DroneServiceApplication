package com.dronetask.drone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class DroneConfig {
    @Bean
    CommandLineRunner commandLineRunner(DroneRepository repository) {
        return args -> {
            Drone d1 = new Drone("QWOE2930421", DroneModel.Cruiserweight,300.9, 75, DroneState.IDLE);
            Drone d2 = new Drone("QWOE2930422", DroneModel.Heavyweight, 450.5, 25, DroneState.IDLE);
            Drone d3 = new Drone("QWOE2930423", DroneModel.Lightweight, 100.0, 99, DroneState.IDLE);
            Drone d4 = new Drone("QWOE2930424", DroneModel.Middleweight, 200.5, 50, DroneState.IDLE);
            repository.saveAll(
                    List.of(d1,d2,d3,d4)
            );
        };
    }
}
