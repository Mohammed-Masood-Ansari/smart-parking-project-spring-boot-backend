package com.smart_tech.smart_parking_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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
    	    regexp = "^[a-zA-Z0-9]+$",
    	    message = "Password must be alphanumeric (only letters and numbers)"
    	)
	private String password;
	

    @NotNull(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid Indian phone number")
	private Long phone;
    
    @NotBlank(message = "Role is required")
	private Long roleId;

	
	public UserRequestDTO() {
		super();
	}


	public UserRequestDTO(String name, String email, String password, Long phone, Long roleId) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.roleId = roleId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public Long getPhone() {
		return phone;
	}



	public void setPhone(Long phone) {
		this.phone = phone;
	}



	public Long getRoleId() {
		return roleId;
	}



	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}



	@Override
	public String toString() {
		return "UserRequestDTO [name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone
				+ ", roleId=" + roleId + "]";
	}
	
	
	
}
