package com.bootcamp.blog.services;

import com.bootcamp.blog.entities.Blog;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlogService {

    List<Blog> findAll();

    Blog save(Blog blog);

    Blog findById(int id) throws EntityNotFoundException;

    ResponseEntity<Blog> deleteById(int id) throws EntityNotFoundException;

}
