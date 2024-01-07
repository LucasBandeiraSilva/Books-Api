package com.lucas.bandeira.api.books.demo.controller;

import com.lucas.bandeira.api.books.demo.entity.Books;
import com.lucas.bandeira.api.books.demo.record.BookRecordDto;
import com.lucas.bandeira.api.books.demo.repositories.BookRepository;
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
    private BookRepository bookRepository;
    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks(){
        var books = bookRepository.findAll();
        if (books.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return  ResponseEntity.status(HttpStatus.OK).body(bookRepository.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> newBook(@RequestBody @Valid BookRecordDto bookRecordDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        var books = new Books();
        BeanUtils.copyProperties(bookRecordDto,books);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookRepository.save(books));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Books>findById(@PathVariable Long id){
        Optional<Books> booksOptional = bookRepository.findById(id);
        if (booksOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.FOUND).body(booksOptional.get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object>updateBookById(@PathVariable Long id, @RequestBody @Valid BookRecordDto bookRecordDto, BindingResult bindingResult ){
        Optional<Books> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            BeanUtils.copyProperties(bookRecordDto,bookOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(bookRepository.save(bookOptional.get()));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

    }
}

