package com.smart_tech.smart_parking_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_tech.smart_parking_system.dto.BookingEntryDTO;
import com.smart_tech.smart_parking_system.dto.BookingResponseDTO;
import com.smart_tech.smart_parking_system.service.ParkingSlotBookingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/booking")
@RequiredArgsConstructor
public class ParkingBookingController {

	private final ParkingSlotBookingService parkingSlotBookingService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<BookingResponseDTO> createBooking(@RequestBody BookingEntryDTO dto) {

		return ResponseEntity.ok(parkingSlotBookingService.createBooking(dto));
	}
}
