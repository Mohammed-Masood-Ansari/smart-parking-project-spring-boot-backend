package com.smart_tech.smart_parking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_tech.smart_parking_system.dto.VehicleRequestDTO;
import com.smart_tech.smart_parking_system.dto.VehicleResponseDTO;
import com.smart_tech.smart_parking_system.service.VehicleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/vehicle")
@RequiredArgsConstructor
public class VehicleController {

	private final VehicleService vehicleService;

	@PostMapping(value = "/saveVehicle")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<VehicleResponseDTO> saveVehicleController(
			@Valid @RequestBody VehicleRequestDTO vehicleRequestDTO) {

		VehicleResponseDTO response = vehicleService.saveVehicleService(vehicleRequestDTO);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
