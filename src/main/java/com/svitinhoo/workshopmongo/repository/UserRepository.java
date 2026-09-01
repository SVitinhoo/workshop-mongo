package com.svitinhoo.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.svitinhoo.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String>{

}
