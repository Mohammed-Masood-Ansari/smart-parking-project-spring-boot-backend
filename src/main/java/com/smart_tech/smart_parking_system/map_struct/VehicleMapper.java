package com.smart_tech.smart_parking_system.map_struct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.smart_tech.smart_parking_system.dto.VehicleRequestDTO;
import com.smart_tech.smart_parking_system.dto.VehicleResponseDTO;
import com.smart_tech.smart_parking_system.entity.Vehicle;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

	@Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Vehicle toVehicle(VehicleRequestDTO dto);
	
	@Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "user.email", target = "userEmail")
    VehicleResponseDTO toVehicleResponseDTO(Vehicle vehicle);
}
