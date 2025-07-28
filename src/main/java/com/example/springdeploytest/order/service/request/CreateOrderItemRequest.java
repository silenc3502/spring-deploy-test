package com.example.springdeploytest.order.service.request;

import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.order.entity.OrderItem;
import com.example.springdeploytest.order.entity.Orders;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderItemRequest {
    final private Long bookId;
    final private Long quantity;
    final private Long price;

    public OrderItem toOrderItem(Book book, Orders orders) {
        return new OrderItem(book, quantity, price, orders);
    }
}
