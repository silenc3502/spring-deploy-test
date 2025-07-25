package com.example.springdeploytest.cart.entity;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Cart(Book book, Long quantity, Account account) {
        this.book = book;
        this.quantity = quantity;
        this.account = account;
    }
}
