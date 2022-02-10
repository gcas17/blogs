package com.bootcamp.blog.services.impl;

import com.bootcamp.blog.entities.Blog;
import com.bootcamp.blog.entities.Post;
import com.bootcamp.blog.repositories.BlogRepository;
import com.bootcamp.blog.repositories.PostRepository;
import com.bootcamp.blog.services.PostService;
import com.bootcamp.blog.util.enums.BlogStatus;
import com.bootcamp.blog.util.enums.PostStatus;
import com.bootcamp.blog.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static com.bootcamp.blog.util.constants.Constants.*;
import static jdk.nashorn.internal.objects.NativeString.trim;
import static org.springframework.util.StringUtils.capitalize;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public Post save(Post post) {

        Optional<Blog> blog = this.blogRepository.findById(post.getBlog().getId());

        if(!blog.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BLOG_NOT_FOUND_ERROR_MESSAGE);
        }

        if(trim(blog.get().getStatus()).toLowerCase().equals(BlogStatus.INACTIVE.getDescription().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, POST_WITH_BLOG_INACTIVE_ERROR_MESSAGE);
        }

        for (Post item : blog.get().getPosts()) {
            validateSameDayPostByBlog(
                    item.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    post.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            );
        }

        if(!validatePostStatus(post.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, POST_STATUS_INVALID_ERROR_MESSAGE);
        }
        post.setStatus(capitalize(trim(post.getStatus()).toLowerCase()));
        return this.postRepository.save(post);
    }

    @Override
    public Post findById(int id) throws EntityNotFoundException {
        Optional<Post> post = this.postRepository.findById(id);
        if(!post.isPresent()) {
            throw new EntityNotFoundException(POST_NOT_FOUND_ERROR_MESSAGE);
        }
        return post.get();
    }

    @Override
    public ResponseEntity<Post> deleteById(int id) throws EntityNotFoundException {
        try {
            this.postRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(POST_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private boolean validatePostStatus(String status) {
        return trim(status).toUpperCase().equals(PostStatus.DRAFT.getDescription().toUpperCase())
                || trim(status).toUpperCase().equals(PostStatus.PUBLISHED.getDescription().toUpperCase());
    }

    private void validateSameDayPostByBlog(LocalDate fecha1, LocalDate fecha2) {
        Period period = Period.between(fecha1, fecha2);
        if(period.getYears() == 0 && period.getMonths() == 0 && period.getDays() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, POST_SAME_DAY_ERROR_MESSAGE);
        }
    }
}
