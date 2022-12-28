package com.dronetask.drone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("SELECT d FROM Drone d WHERE d.serialNumber = ?1")
    Optional<Drone> findDroneBySerialNumber(String serialNumber);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Drone d SET d.droneState = :state where  d.serialNumber= :serialNumber")
    int updateDroneState(@Param("serialNumber") String serialNumber, @Param("state") DroneState state);

    List<Drone> findAllByDroneState(@Param("drone_state") DroneState droneState);
}
