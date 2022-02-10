package com.bootcamp.blog.services;

import com.bootcamp.blog.entities.Post;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    List<Post> findAll();

    Post save(Post post);

    Post findById(int id) throws EntityNotFoundException;

    ResponseEntity<Post> deleteById(int id) throws EntityNotFoundException;

}
