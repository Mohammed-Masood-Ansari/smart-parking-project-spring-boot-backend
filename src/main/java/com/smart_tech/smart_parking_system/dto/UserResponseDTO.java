package com.smart_tech.smart_parking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

	private Long id;
	private String name;
	private String email;
	private String phone;
	private String role;


}
