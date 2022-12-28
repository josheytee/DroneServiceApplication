package com.dronetask.medication;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MedicationConfig {
    @Bean
    CommandLineRunner mcommandLineRunner(MedicationRepository repository) {
        return args -> {
            Medication m1 = new Medication( "paracetamol", 29.0, "mapp1", "thant imaeg", null);

            repository.saveAll(
                    List.of(m1)
            );
        };
    }
}
