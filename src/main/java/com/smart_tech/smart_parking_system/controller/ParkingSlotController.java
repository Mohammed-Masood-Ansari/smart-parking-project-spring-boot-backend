package com.smart_tech.smart_parking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_tech.smart_parking_system.dto.ParkingSlotRequestDTO;
import com.smart_tech.smart_parking_system.dto.ParkingSlotResponseDTO;
import com.smart_tech.smart_parking_system.service.ParkingSlotService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/parkingslot")
@RequiredArgsConstructor
public class ParkingSlotController {

	private final ParkingSlotService parkingSlotService;
	
	@PostMapping("/register")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ParkingSlotResponseDTO> registerParkingSlot(
			@Valid @RequestBody ParkingSlotRequestDTO dto) {

		ParkingSlotResponseDTO response =
				parkingSlotService.registerParkingSlot(dto);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
