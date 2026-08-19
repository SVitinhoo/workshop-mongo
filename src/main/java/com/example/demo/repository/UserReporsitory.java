package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.domain.User;

public interface UserReporsitory extends MongoRepository<User, String>{

}
