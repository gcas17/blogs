package com.bootcamp.blog.controllers;

import com.bootcamp.blog.entities.Blog;
import com.bootcamp.blog.services.BlogService;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.bootcamp.blog.util.constants.Constants.BLOG_NOT_FOUND_ERROR_MESSAGE;

@RestController
@RequestMapping("blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<Blog> findAll() {
        return this.blogService.findAll();
    }

    @GetMapping("/{id}")
    public Blog findById(@PathVariable int id) {
        try {
            return this.blogService.findById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, BLOG_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }

    @PostMapping
    public Blog save(@RequestBody Blog blog){
        return this.blogService.save(blog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Blog> deleteById(@PathVariable("id") int id) {
        try {
            return this.blogService.deleteById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, BLOG_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }
}
