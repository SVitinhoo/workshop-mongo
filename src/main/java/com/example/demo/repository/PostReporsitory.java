package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.domain.Post;

public interface PostReporsitory extends MongoRepository<Post, String>{

}
