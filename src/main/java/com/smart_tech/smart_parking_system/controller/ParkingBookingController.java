package com.smart_tech.smart_parking_system.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_tech.smart_parking_system.dto.BookingEntryDTO;
import com.smart_tech.smart_parking_system.dto.ExitBookingDTO;
import com.smart_tech.smart_parking_system.entity.Booking;
import com.smart_tech.smart_parking_system.service.ParkingSlotBookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/booking")
@RequiredArgsConstructor
public class ParkingBookingController {

	private final ParkingSlotBookingService parkingSlotBookingService;

	@PostMapping("/book")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Booking> createBooking(
	        @Valid @RequestBody BookingEntryDTO dto) {

	    return ResponseEntity.ok(
	    		parkingSlotBookingService.createBooking(dto)
	    );
	}
	
	@PutMapping("/exit")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Booking> exitBooking(
	        @Valid @RequestBody ExitBookingDTO dto) {

	    return ResponseEntity.ok(
	    		parkingSlotBookingService.exitBooking(dto)
	    );
	}
}
