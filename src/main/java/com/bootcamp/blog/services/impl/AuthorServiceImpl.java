package com.bootcamp.blog.services.impl;

import com.bootcamp.blog.entities.Author;
import com.bootcamp.blog.repositories.AuthorRepository;
import com.bootcamp.blog.services.AuthorService;
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

import static com.bootcamp.blog.util.constants.Constants.AUTHOR_INVALID_BIRTHDAY_ERROR_MESSAGE;
import static com.bootcamp.blog.util.constants.Constants.AUTHOR_NOT_FOUND_ERROR_MESSAGE;
import static com.bootcamp.blog.util.constants.Constants.LEGAL_AGE;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author save(Author author) {
        Period period = Period.between(
                author.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()
        );
        if(period.getYears() < LEGAL_AGE) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, AUTHOR_INVALID_BIRTHDAY_ERROR_MESSAGE);
        }
        return this.authorRepository.save(author);
    }

    @Override
    public Author findById(int id) throws EntityNotFoundException {
        Optional<Author> author = this.authorRepository.findById(id);
        if(!author.isPresent()) {
            throw new EntityNotFoundException(AUTHOR_NOT_FOUND_ERROR_MESSAGE);
        }
        return author.get();
    }

    @Override
    public ResponseEntity<Author> deleteById(int id)  throws EntityNotFoundException {
        try {
            this.authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException(AUTHOR_NOT_FOUND_ERROR_MESSAGE);
        }
    }
}
