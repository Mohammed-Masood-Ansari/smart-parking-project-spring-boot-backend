package com.smart_tech.smart_parking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_tech.smart_parking_system.dto.UserRequestDTO;
import com.smart_tech.smart_parking_system.dto.UserResponseDTO;
import com.smart_tech.smart_parking_system.entity.User;
import com.smart_tech.smart_parking_system.map_struct.UserMapper;
import com.smart_tech.smart_parking_system.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthController {

	private final UserService userService;
	
	private final UserMapper userMapper;
	
	private final PasswordEncoder passwordEncoder;
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> registerUserController(@RequestBody @Valid UserRequestDTO requestDTO){
		
		User user=userMapper.toUser(requestDTO);
		
		String pass = passwordEncoder.encode(requestDTO.getPassword());
		
		user.setPassword(pass);
		
		User  user2=userService.registerUserService(user);
		
		UserResponseDTO response=userMapper.toUserResponseDTO(user2);
		
		 return new ResponseEntity(response, HttpStatus.CREATED);
		
	}
	
}
