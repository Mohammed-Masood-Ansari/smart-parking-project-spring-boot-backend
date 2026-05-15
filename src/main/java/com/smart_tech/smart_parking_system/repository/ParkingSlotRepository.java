package com.smart_tech.smart_parking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_tech.smart_parking_system.entity.ParkingSlot;
import com.smart_tech.smart_parking_system.enums.SlotStatus;
import com.smart_tech.smart_parking_system.enums.VehicleType;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {

	Optional<ParkingSlot> findBySlotNumber(String slotNumber);

	boolean existsBySlotNumber(String slotNumber);

	Optional<ParkingSlot> findFirstByStatusAndSlotType(SlotStatus status, VehicleType slotType);

//	boolean existsByVehicleAndStatus(Vehicle vehicle, BookingStatus status);
}
