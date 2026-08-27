package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
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
	
	public User Insert(User obj) {
		return userReporsitory.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		userReporsitory.deleteById(id);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	
}