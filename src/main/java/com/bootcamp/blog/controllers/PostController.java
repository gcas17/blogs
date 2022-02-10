package com.bootcamp.blog.controllers;

import com.bootcamp.blog.entities.Post;
import com.bootcamp.blog.services.PostService;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.bootcamp.blog.util.constants.Constants.POST_NOT_FOUND_ERROR_MESSAGE;

@RestController
@RequestMapping("publicaciones")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> findAll() {
        return this.postService.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable int id) {
        try {
            return this.postService.findById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, POST_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }

    @PostMapping
    public Post save(@RequestBody Post post){
        return this.postService.save(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deleteById(@PathVariable("id") int id) {
        try {
            return this.postService.deleteById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, POST_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }
}
