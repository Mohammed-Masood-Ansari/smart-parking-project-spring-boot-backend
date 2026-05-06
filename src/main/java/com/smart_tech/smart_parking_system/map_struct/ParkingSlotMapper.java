package com.smart_tech.smart_parking_system.map_struct;

import org.mapstruct.Mapper;

import com.smart_tech.smart_parking_system.dto.ParkingSlotRequestDTO;
import com.smart_tech.smart_parking_system.dto.ParkingSlotResponseDTO;
import com.smart_tech.smart_parking_system.entity.ParkingSlot;

@Mapper(componentModel = "spring")
public interface ParkingSlotMapper {


    ParkingSlot toParkingSlot(ParkingSlotRequestDTO dto);

    ParkingSlotResponseDTO toParkingSlotResponseDTO(ParkingSlot entity);
	
}
