package com.smart_tech.smart_parking_system.service;

import org.springframework.stereotype.Service;

import com.smart_tech.smart_parking_system.entity.User;
import com.smart_tech.smart_parking_system.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User registerUserService(User user) {
		return userRepository.saveAndFlush(user);
	}

}
