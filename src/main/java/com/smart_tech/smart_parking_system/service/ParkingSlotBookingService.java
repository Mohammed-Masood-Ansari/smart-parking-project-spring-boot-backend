package com.smart_tech.smart_parking_system.service;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.smart_tech.smart_parking_system.dto.BookingEntryDTO;
import com.smart_tech.smart_parking_system.dto.BookingResponseDTO;
import com.smart_tech.smart_parking_system.entity.Booking;
import com.smart_tech.smart_parking_system.entity.ParkingSlot;
import com.smart_tech.smart_parking_system.entity.User;
import com.smart_tech.smart_parking_system.entity.Vehicle;
import com.smart_tech.smart_parking_system.enums.BookingStatus;
import com.smart_tech.smart_parking_system.enums.SlotStatus;
import com.smart_tech.smart_parking_system.repository.ParkingSlotBookingRepository;
import com.smart_tech.smart_parking_system.repository.ParkingSlotRepository;
import com.smart_tech.smart_parking_system.repository.UserRepository;
import com.smart_tech.smart_parking_system.repository.VehicleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParkingSlotBookingService {

    private final VehicleRepository vehicleRepository;

    private final ParkingSlotBookingRepository parkingSlotBookingRepository;

    private final ParkingSlotRepository parkingSlotRepository;

    private final UserRepository userRepository;

    public BookingResponseDTO createBooking(BookingEntryDTO dto) {

        // Get logged-in user email
        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String email = authentication.getName();

        // Find logged-in user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Find vehicle
        Vehicle vehicle = vehicleRepository
                .findByVehicleNumber(dto.getVehicleNumber())
                .orElseThrow(() ->
                        new RuntimeException("Vehicle not found"));

        // Check active booking
        boolean alreadyBooked =
                parkingSlotRepository
                        .existsByVehicleAndStatus(
                                vehicle,
                                BookingStatus.ACTIVE
                        );

        if (alreadyBooked) {
            throw new RuntimeException(
                    "Vehicle already parked"
            );
        }

        // Find available slot
        ParkingSlot slot = parkingSlotRepository
                .findFirstByStatusAndSlotType(
                        SlotStatus.AVAILABLE,
                        vehicle.getVehicleType()
                )
                .orElseThrow(() ->
                        new RuntimeException(
                                "No slot available for "
                                        + vehicle.getVehicleType()
                        ));

        // Update slot status
        slot.setStatus(SlotStatus.OCCUPIED);

        parkingSlotRepository.save(slot);

        // Create booking
        Booking booking = new Booking();

        booking.setUser(user);

        booking.setVehicle(vehicle);

        booking.setSlot(slot);

        booking.setStartTime(LocalDateTime.now());

        booking.setStatus(BookingStatus.ACTIVE);

        booking.setAmount(0.0);

        // Save booking
        Booking savedBooking =
                parkingSlotBookingRepository
                        .save(booking);

        return new BookingResponseDTO(
                savedBooking.getId(),
                savedBooking.getVehicle()
                        .getVehicleNumber(),
                savedBooking.getSlot()
                        .getSlotNumber(),
                savedBooking.getStartTime(),
                savedBooking.getEndTime(),
                0L,
                savedBooking.getAmount(),
                savedBooking.getStatus()
        );
    }
}