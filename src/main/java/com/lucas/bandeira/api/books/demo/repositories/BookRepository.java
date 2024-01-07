package com.lucas.bandeira.api.books.demo.repositories;

import com.lucas.bandeira.api.books.demo.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Books, Long> {
}
