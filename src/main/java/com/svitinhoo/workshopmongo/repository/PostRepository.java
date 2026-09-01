package com.svitinhoo.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.svitinhoo.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' }}")
	List<Post> serachTitle(String text);
	
	@Query("{ $and: [{ date: {$gte:  ?1}}, { date: { $lte: ?2}}, "
			+ "{$or: ["
			+ "{ 'title':{ $regex: ?0, $options: 'i' }}, "
			+ "{ 'body': { $regex: ?0, $options: 'i' }},"
			+ "{ 'comments.text': { $regex: ?0, $options: 'i' }}]} ]}")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
