package com.smart_tech.smart_parking_system.dto;

public class UserResponseDTO {

	private Long id;
	private String name;
	private String email;
	private Long phone;
	private String role;

	public UserResponseDTO() {
		super();
	}

	public UserResponseDTO(Long id, String name, String email, Long phone, String role) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserResponseDTO [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", role="
				+ role + "]";
	}

}
