package com.example.demo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		User temp = new User("1", "maria", "maria@gmail.com");
		User temp2 = new User("2", "alex", "alex@gmail.com");
		User temp3 = new User("3", "jhonatan", "jhonatan@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(temp, temp2, temp3));
		return ResponseEntity.ok().body(list);
	}
}
