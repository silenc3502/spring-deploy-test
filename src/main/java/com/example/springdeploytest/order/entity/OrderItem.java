package com.example.springdeploytest.order.entity;

import com.example.springdeploytest.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // OrderItem의 경우엔 수량, 항목(bookId), 가격, 어디의 주문인지 여부 (ordersId)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private Long quantity;
    private Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;

    public OrderItem(Book book, Long quantity, Long price, Orders orders) {
        this.book = book;
        this.quantity = quantity;
        this.price = price;
        this.orders = orders;
    }
}
