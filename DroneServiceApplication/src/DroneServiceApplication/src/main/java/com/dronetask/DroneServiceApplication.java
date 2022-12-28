package com.dronetask;

import com.dronetask.drone.Drone;
import com.dronetask.drone.DroneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@SpringBootApplication
public class DroneServiceApplication {
    @Autowired
    private DroneRepository droneRepository;

    static final Logger log = LoggerFactory.getLogger(SchedulingConfiguration.class);

    public static void main(String[] args) {
        SpringApplication.run(DroneServiceApplication.class, args);
    }

    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTaskAsync() throws InterruptedException {

        List<Drone> droneList = droneRepository.findAll();
        for(Drone drone : droneList){
            log.info("Batery level--: for drone with SerialNumber  " + drone.getSerialNumber() + "  is " + drone.getBatteryLevel() + "%");
        }
        Thread.sleep(5000);
    }

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingConfiguration {

}

