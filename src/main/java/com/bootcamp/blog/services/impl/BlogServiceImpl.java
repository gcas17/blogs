package com.bootcamp.blog.services.impl;

import com.bootcamp.blog.entities.Author;
import com.bootcamp.blog.entities.Blog;
import com.bootcamp.blog.repositories.AuthorRepository;
import com.bootcamp.blog.repositories.BlogRepository;
import com.bootcamp.blog.services.BlogService;
import com.bootcamp.blog.util.enums.BlogStatus;
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
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Blog> findAll() {
        return this.blogRepository.findAll();
    }

    @Override
    public Blog save(Blog blog) {
        Optional<Author> author = this.authorRepository.findById(blog.getAuthor().getId());

        if(!author.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AUTHOR_NOT_FOUND_ERROR_MESSAGE);
        }

        if(author.get().getBlogs().size() >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BLOG_LIMIT_BY_AUTHOR_ERROR_MESSAGE);
        }

        if(!validateBlogStatus(blog.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BLOG_STATUS_INVALID_ERROR_MESSAGE);
        }

        blog.setStatus(capitalize(trim(blog.getStatus()).toLowerCase()));
        return this.blogRepository.save(blog);
    }

    @Override
    public Blog findById(int id) throws EntityNotFoundException {
        Optional<Blog> blog = this.blogRepository.findById(id);
        if(!blog.isPresent()) {
            throw new EntityNotFoundException(BLOG_NOT_FOUND_ERROR_MESSAGE);
        }
        return blog.get();
    }

    @Override
    public ResponseEntity<Blog> deleteById(int id) throws EntityNotFoundException {
        try {
            this.blogRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(BLOG_NOT_FOUND_ERROR_MESSAGE);
        }
    }

    private boolean validateBlogStatus(String status) {
        return trim(status).toUpperCase().equals(BlogStatus.ACTIVE.getDescription().toUpperCase())
                || trim(status).toUpperCase().equals(BlogStatus.INACTIVE.getDescription().toUpperCase());
    }
}
