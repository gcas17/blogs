package com.bootcamp.blog.services.impl;

import com.bootcamp.blog.entities.Comment;
import com.bootcamp.blog.entities.Post;
import com.bootcamp.blog.repositories.CommentRepository;
import com.bootcamp.blog.repositories.PostRepository;
import com.bootcamp.blog.services.CommentService;
import com.bootcamp.blog.util.enums.CommentStatus;
import com.bootcamp.blog.util.enums.PostStatus;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static com.bootcamp.blog.util.constants.Constants.*;
import static jdk.nashorn.internal.objects.NativeString.trim;
import static org.springframework.util.StringUtils.capitalize;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Comment save(Comment comment) {

        Optional<Post> post = this.postRepository.findById(comment.getPost().getId());

        if(!post.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, POST_NOT_FOUND_ERROR_MESSAGE);
        }

        if(!trim(post.get().getStatus().toLowerCase()).equals(PostStatus.PUBLISHED.getDescription().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, COMMENT__WITH_POST_INACTIVE_ERROR_MESSAGE);
        }


        if(!validateCommentStatus(comment.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, COMMENT_STATUS_INVALID_ERROR_MESSAGE);
        }
        comment.setStatus(capitalize(trim(comment.getStatus()).toLowerCase()));
        return this.commentRepository.save(comment);
    }

    @Override
    public Comment findById(int id) throws EntityNotFoundException {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if(!comment.isPresent()) {
            throw new EntityNotFoundException(COMMENT_NOT_FOUND_ERROR_MESSAGE);
        }
        return comment.get();
    }

    @Override
    public ResponseEntity<Comment> deleteById(int id) throws EntityNotFoundException {
        try {
            this.commentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(COMMENT_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private boolean validateCommentStatus(String status) {
        return trim(status).toUpperCase().equals(CommentStatus.ACTIVE.getDescription().toUpperCase())
                || trim(status).toUpperCase().equals(CommentStatus.INACTIVE.getDescription().toUpperCase());
    }
}
