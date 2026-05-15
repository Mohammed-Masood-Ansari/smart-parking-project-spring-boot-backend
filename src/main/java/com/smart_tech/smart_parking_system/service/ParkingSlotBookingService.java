package com.smart_tech.smart_parking_system.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smart_tech.smart_parking_system.dto.BookingEntryDTO;
import com.smart_tech.smart_parking_system.dto.ExitBookingDTO;
import com.smart_tech.smart_parking_system.entity.Booking;
import com.smart_tech.smart_parking_system.entity.ParkingSlot;
import com.smart_tech.smart_parking_system.entity.User;
import com.smart_tech.smart_parking_system.entity.Vehicle;
import com.smart_tech.smart_parking_system.enums.BookingStatus;
import com.smart_tech.smart_parking_system.enums.VehicleType;
import com.smart_tech.smart_parking_system.repository.BookingRepository;
import com.smart_tech.smart_parking_system.repository.ParkingSlotRepository;
import com.smart_tech.smart_parking_system.repository.UserRepository;
import com.smart_tech.smart_parking_system.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingSlotBookingService {

	    private final BookingRepository bookingRepository;
	    private final UserRepository userRepository;
	    private final VehicleRepository vehicleRepository;
	    private final ParkingSlotRepository slotRepository;

	    public Booking createBooking(BookingEntryDTO dto) {

	        // Logged-in user
	        Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

	        String email = authentication.getName();

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                        new RuntimeException("User not logged in please login and try again"));

	        // Find parking slot
	        ParkingSlot slot = slotRepository
	                .findBySlotNumber(dto.getSlotNumber())
	                .orElseThrow(() ->
	                        new RuntimeException("Slot is not Found"));

	        // Check slot availability
	        if (slot.getStatus().equals("OCCUPIED")) {
	            throw new RuntimeException("Slot already occupied");
	        }

	        // Find vehicle
	        Vehicle vehicle = vehicleRepository
	                .findByVehicleNumber(dto.getVehicleNumber())
	                .orElse(null);

	        // Create vehicle if not exists
	        if (vehicle == null) {

	            vehicle = new Vehicle();

	            vehicle.setVehicleNumber(dto.getVehicleNumber());
	            vehicle.setVehicleType(dto.getVehicleType());
	            vehicle.setUser(user);

	            vehicle = vehicleRepository.save(vehicle);
	        }

	        // Create booking
	        Booking booking = new Booking();

	        booking.setUser(user);
	        booking.setVehicle(vehicle);
	        booking.setSlot(slot);
	        booking.setStatus(BookingStatus.ACTIVE);

	        // Mark slot occupied
	        slot.setStatus(slot.getStatus().OCCUPIED);
	        slotRepository.save(slot);

	        return bookingRepository.save(booking);
	    }
	    
	    /**
	     * Exit booking and calculate amount
	     * @param dto
	     * @return
	     */
	    public Booking exitBooking(ExitBookingDTO dto) {

	        // Logged-in user
	        Authentication authentication =
	                SecurityContextHolder.getContext().getAuthentication();

	        String email = authentication.getName();

	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() ->
	                        new RuntimeException(
	                                "User not logged in please login again"));

	        // Find active booking
	        Booking booking = bookingRepository
	                .findByVehicleVehicleNumberAndStatus(
	        	           	dto.getVehicleNumber(),
	        	            BookingStatus.ACTIVE
	        	    )
	                .orElseThrow(() ->
	                        new RuntimeException("Active booking not found"));

	        // Security check
	        if (!booking.getUser().getId().equals(user.getId())) {
	            throw new RuntimeException(
	                    "You cannot exit another user's vehicle");
	        }

	        // Exit time
	        LocalDateTime endTime = LocalDateTime.now();

	        booking.setEndTime(endTime);

	        // Calculate duration
	        long hours = Duration
	                .between(booking.getStartTime(), endTime)
	                .toHours();

	        // Minimum 1 hour
	        if (hours == 0) {
	            hours = 1;
	        }

	        // Rate calculation
	        VehicleType vehicleType =
	                booking.getVehicle().getVehicleType();

	        double ratePerHour = 0;

	        switch (vehicleType) {

	            case CAR:
	                ratePerHour = 50;
	                break;

	            case AUTO:
	                ratePerHour = 40;
	                break;

	            case BIKE:
	                ratePerHour = 20;
	                break;

	            default:
	                throw new RuntimeException("Invalid vehicle type");
	        }

	        double totalAmount = hours * ratePerHour;

	        booking.setAmount(totalAmount);

	        // Complete booking
	        booking.setStatus(BookingStatus.COMPLETED);

	        // Make slot available
	        ParkingSlot slot = booking.getSlot();

	        slot.setStatus(slot.getStatus().AVAILABLE);

	        slotRepository.save(slot);

	        return bookingRepository.save(booking);
	    }
}