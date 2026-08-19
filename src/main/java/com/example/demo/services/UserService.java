package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserReporsitory;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	private final UserReporsitory userReporsitory;

	UserService(UserReporsitory userReporsitory) {
		this.userReporsitory = userReporsitory;
	}

	public List<User> finaAll() {
		return userReporsitory.findAll();
	}
	
	public User findById(String id) {
		Optional<User> user = userReporsitory.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}
