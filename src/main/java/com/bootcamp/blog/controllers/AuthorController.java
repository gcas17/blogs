package com.bootcamp.blog.controllers;

import com.bootcamp.blog.entities.Author;
import com.bootcamp.blog.services.AuthorService;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.bootcamp.blog.util.constants.Constants.AUTHOR_NOT_FOUND_ERROR_MESSAGE;

@RestController
@RequestMapping("autores")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author findById(@PathVariable int id) {
        try {
            return this.authorService.findById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, AUTHOR_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }

    @PostMapping
    public Author save(@RequestBody Author author) {
        return this.authorService.save(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteById(@PathVariable("id") int id) {
        try {
            return this.authorService.deleteById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, AUTHOR_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }
}
