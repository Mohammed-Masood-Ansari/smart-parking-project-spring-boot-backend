package com.smart_tech.smart_parking_system.dto;

import com.smart_tech.smart_parking_system.enums.VehicleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntryDTO {

    @NotBlank(message = "Vehicle number is required")
    @Pattern(
        regexp = "^[A-Z0-9-]+$",
        message = "Vehicle number must be uppercase"
    )
    private String vehicleNumber;

    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;
}