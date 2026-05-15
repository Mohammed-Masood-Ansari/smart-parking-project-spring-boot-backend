package com.smart_tech.smart_parking_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExitBookingDTO {

	@NotBlank(message = "Vehicle number is required")
	private String vehicleNumber;
	
}
