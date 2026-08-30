package com.example.demo.resources;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Post;
import com.example.demo.services.PostService;


@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	private final PostService postService;

	PostResource(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = "/{id}") 
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = postService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
