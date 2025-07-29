package com.example.springdeploytest.book.repository;

import com.example.springdeploytest.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(
            value = "select b from Book b join fetch b.account",
            countQuery = "select count(b) from Book b"
    )
    Page<Book> findAllWithAccount(Pageable pageable);
}
