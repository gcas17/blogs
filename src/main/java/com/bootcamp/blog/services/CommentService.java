package com.bootcamp.blog.services;

import com.bootcamp.blog.entities.Comment;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    List<Comment> findAll();

    Comment save(Comment comment);

    Comment findById(int id) throws EntityNotFoundException;

    ResponseEntity<Comment> deleteById(int id) throws EntityNotFoundException;

}
