package com.smart_tech.smart_parking_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart_tech.smart_parking_system.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

	 Optional<Vehicle> findByVehicleNumber(String vehicleNumber);
	
}
