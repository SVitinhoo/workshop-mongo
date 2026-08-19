package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserReporsitory;

@Service
public class UserService {

	private final UserReporsitory userReporsitory;

	UserService(UserReporsitory userReporsitory) {
		this.userReporsitory = userReporsitory;
	}

	public List<User> finaAll() {
		return userReporsitory.findAll();
	}
}
