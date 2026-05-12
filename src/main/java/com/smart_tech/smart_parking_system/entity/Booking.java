package com.smart_tech.smart_parking_system.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.smart_tech.smart_parking_system.enums.BookingStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreationTimestamp
	private LocalDateTime startTime;

	private LocalDateTime endTime;

	@Enumerated(EnumType.STRING)
	private BookingStatus status;

	private Double amount;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "slot_id")
	private ParkingSlot slot;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	

}
