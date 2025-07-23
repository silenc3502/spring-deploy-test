package com.example.springdeploytest.book.repository;

import com.example.springdeploytest.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
