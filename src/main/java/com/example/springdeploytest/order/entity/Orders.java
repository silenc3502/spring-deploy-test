package com.example.springdeploytest.order.entity;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Orders {
    // 일단 mysql에 order 라는 쿼리 예약어가 존재함.
    // 그렇기 때문에 orders 로 만들어지게 구성하여 예약어를 회피해야함.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private Long quantity;
    private Long price;

    public Orders(Book book, Account account, Long quantity, Long price) {
        this.book = book;
        this.account = account;
        this.quantity = quantity;
        this.price = price;
    }
}
