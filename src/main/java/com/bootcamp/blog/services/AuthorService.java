package com.bootcamp.blog.services;

import com.bootcamp.blog.entities.Author;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author save(Author author);

    Author findById(int id) throws EntityNotFoundException;

    ResponseEntity<Author> deleteById(int id) throws EntityNotFoundException;

}
