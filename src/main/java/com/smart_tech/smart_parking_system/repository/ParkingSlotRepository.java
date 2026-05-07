package com.smart_tech.smart_parking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_tech.smart_parking_system.entity.ParkingSlot;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

	Optional<ParkingSlot> findBySlotNumber(String slotNumber);

	boolean existsBySlotNumber(String slotNumber);
}
