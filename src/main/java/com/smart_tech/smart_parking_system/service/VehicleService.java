package com.smart_tech.smart_parking_system.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smart_tech.smart_parking_system.dto.VehicleRequestDTO;
import com.smart_tech.smart_parking_system.dto.VehicleResponseDTO;
import com.smart_tech.smart_parking_system.entity.User;
import com.smart_tech.smart_parking_system.entity.Vehicle;
import com.smart_tech.smart_parking_system.map_struct.VehicleMapper;
import com.smart_tech.smart_parking_system.repository.UserRepository;
import com.smart_tech.smart_parking_system.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleService {

	private final VehicleRepository vehicleRepository;
	private final UserRepository userRepository;
	private final VehicleMapper vehicleMapper;

	public boolean isVehicleNumberExists(String vehicleNumber) {
		return vehicleRepository.findByVehicleNumber(vehicleNumber).isPresent();
	}

	/**
	 * This method saves a vehicle to the database. It first retrieves the
	 * authenticated user's email from the security context, then fetches the
	 * corresponding User entity from the database. If the user is not found, it
	 * throws a RuntimeException. Next, it maps the VehicleRequestDTO to a Vehicle
	 * entity, sets the user for the vehicle, and saves it to the database. Finally,
	 * it maps the saved Vehicle entity to a VehicleResponseDTO and returns it.
	 * 
	 * @param vehicleRequestDTO
	 * @return
	 */
	public VehicleResponseDTO saveVehicleService(VehicleRequestDTO vehicleRequestDTO) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String email = authentication.getName();
		
		System.out.println("saveVehicle Authentication: " + authentication);
		
		System.out.println("saveVehicle Email: " + email);

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("you are not authenticated please login and try again"));
		
		vehicleRepository.findByVehicleNumber(vehicleRequestDTO.getVehicleNumber())
				.ifPresent(v -> {
					throw new RuntimeException("Vehicle number already exists");
				});
		

		Vehicle vehicle = vehicleMapper.toVehicle(vehicleRequestDTO);

		vehicle.setUser(user);

		Vehicle savedVehicle = vehicleRepository.save(vehicle);

		return vehicleMapper.toVehicleResponseDTO(savedVehicle);
	}

}
