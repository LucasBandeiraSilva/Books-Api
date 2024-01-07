package com.lucas.bandeira.api.books.demo.service;

import com.lucas.bandeira.api.books.demo.BookRecord.BookRecordDto;
import com.lucas.bandeira.api.books.demo.entity.Books;
import com.lucas.bandeira.api.books.demo.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<List<Books>> getAllBooks() {
        var books = bookRepository.findAll();
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }

    public ResponseEntity<Object> newBook( BookRecordDto bookRecordDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        var books = new Books();
        BeanUtils.copyProperties(bookRecordDto, books);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(books));
    }

    public ResponseEntity<Books> findById(Long id) {
        Optional<Books> booksOptional = bookRepository.findById(id);
        if (booksOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(booksOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    public ResponseEntity<Object> updateBookById( Long id,  BookRecordDto bookRecordDto, BindingResult bindingResult) {
        Optional<Books> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            BeanUtils.copyProperties(bookRecordDto, bookOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(bookOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
    }

    public ResponseEntity<Object> DeleteById(Long id) {
        Optional<Books> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("BOOK HAS BEEN DELETED!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BOOK DOES NOT EXIST!");
    }
}
