package com.bootcamp.blog.controllers;

import com.bootcamp.blog.entities.Comment;
import com.bootcamp.blog.services.CommentService;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.bootcamp.blog.util.constants.Constants.COMMENT_NOT_FOUND_ERROR_MESSAGE;

@RestController
@RequestMapping("comentarios")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> findAll() {
        return this.commentService.findAll();
    }

    @GetMapping("/{id}")
    public Comment findById(@PathVariable int id) {
        try {
            return this.commentService.findById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, COMMENT_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }

    @PostMapping
    public Comment save(@RequestBody Comment comment){
        return this.commentService.save(comment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteById(@PathVariable("id") int id) {
        try {
            return this.commentService.deleteById(id);
        } catch(EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, COMMENT_NOT_FOUND_ERROR_MESSAGE, ex);
        }
    }
}
