package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostReporsitory;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	private final PostReporsitory repo;

	PostService(PostReporsitory repo) {
		this.repo = repo;
	}
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
}