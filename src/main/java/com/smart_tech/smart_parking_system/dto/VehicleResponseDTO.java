package com.smart_tech.smart_parking_system.dto;

import com.smart_tech.smart_parking_system.enums.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {

    private Long id;

    private String vehicleNumber;

    private VehicleType vehicleType;

    private String brand;

    private String color;

    private Long userId;

    private String userName;

    private String userEmail;
}