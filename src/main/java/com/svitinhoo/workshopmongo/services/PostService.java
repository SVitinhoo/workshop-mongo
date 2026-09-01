package com.svitinhoo.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.svitinhoo.workshopmongo.domain.Post;
import com.svitinhoo.workshopmongo.repository.PostRepository;
import com.svitinhoo.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	private final PostRepository repo;

	PostService(PostRepository repo) {
		this.repo = repo;
	}
	
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.serachTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}