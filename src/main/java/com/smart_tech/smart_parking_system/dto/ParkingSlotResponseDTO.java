package com.smart_tech.smart_parking_system.dto;

import com.smart_tech.smart_parking_system.enums.SlotStatus;
import com.smart_tech.smart_parking_system.enums.VehicleType;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ParkingSlotResponseDTO {

	private Long id;
    private String slotNumber;
    private SlotStatus status;
    private VehicleType slotType;
}
