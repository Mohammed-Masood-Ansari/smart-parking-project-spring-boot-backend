package com.smart_tech.smart_parking_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 to 50 characters")
	private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
	private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 15, message = "Password must be between 8 to 15 characters")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@#$%^&+=]{8,15}$",
        message = "Password must contain letters, numbers, and can include special characters"
    )
    private String password;
	

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
	private String phone;
    
    @NotNull(message = "Role is required")
	private Long roleId;
}
