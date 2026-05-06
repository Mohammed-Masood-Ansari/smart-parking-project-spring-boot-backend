package com.smart_tech.smart_parking_system.map_struct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.smart_tech.smart_parking_system.dto.UserRequestDTO;
import com.smart_tech.smart_parking_system.dto.UserResponseDTO;
import com.smart_tech.smart_parking_system.entity.Role;
import com.smart_tech.smart_parking_system.entity.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "roleId", target = "role")
    User toUser(UserRequestDTO dto);

    default Role map(Long roleId) {
        if (roleId == null) return null;

        Role role = new Role();
        role.setId(roleId);
        return role;
    }

    @Mapping(source = "role", target = "role")
    UserResponseDTO toUserResponseDTO(User user);

    default String map(Role role) {
        return (role != null && role.getName() != null)
                ? role.getName().name()
                : null;
    }
}