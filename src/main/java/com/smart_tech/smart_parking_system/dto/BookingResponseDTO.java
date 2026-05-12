package com.smart_tech.smart_parking_system.dto;

import java.time.LocalDateTime;

import com.smart_tech.smart_parking_system.enums.BookingStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {

	private Long bookingId;

	private String vehicleNumber;

	private String slotNumber;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	private Long totalHours;

	private Double totalAmount;

	private BookingStatus status;
}
