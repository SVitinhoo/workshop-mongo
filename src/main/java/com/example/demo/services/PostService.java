package com.example.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostReporsitory;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	private final PostReporsitory postRepository;

	PostService(PostReporsitory repo) {
		this.postRepository = repo;
	}
	
	public Post findById(String id) {
		Optional<Post> post = postRepository.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
}