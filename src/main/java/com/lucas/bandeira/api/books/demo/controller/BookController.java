package com.lucas.bandeira.api.books.demo.controller;

import com.lucas.bandeira.api.books.demo.entity.Books;
import com.lucas.bandeira.api.books.demo.BookRecord.BookRecordDto;
import com.lucas.bandeira.api.books.demo.repositories.BookRepository;
import com.lucas.bandeira.api.books.demo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<Object> newBook(@RequestBody @Valid BookRecordDto bookRecordDto, BindingResult bindingResult) {
        return bookService.newBook(bookRecordDto, bindingResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> findById(@PathVariable Long id) {
        return  bookService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBookById(@PathVariable Long id, @RequestBody @Valid BookRecordDto bookRecordDto, BindingResult bindingResult) {
       return bookService.updateBookById(id,bookRecordDto,bindingResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> DeleteById(@PathVariable Long id) {
        return bookService.DeleteById(id);
    }
}

