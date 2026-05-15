package com.smart_tech.smart_parking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_tech.smart_parking_system.entity.Booking;
import com.smart_tech.smart_parking_system.enums.BookingStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	 Optional<Booking> findByVehicleVehicleNumberAndStatus(
	            String vehicleNumber,
	            BookingStatus status
	    );
}
