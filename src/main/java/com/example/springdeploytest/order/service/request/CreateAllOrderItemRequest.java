package com.example.springdeploytest.order.service.request;

import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.order.entity.OrderItem;
import com.example.springdeploytest.order.entity.Orders;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrderItemRequest {
    final private List<CreateOrderItemRequest> orderItems;

    public List<OrderItem> toOrderItemList(List<Book> bookList, Orders orders) {
        Map<Long, Book> bookMap =
                bookList.stream()
                        .collect(
                                Collectors.toMap(
                                        Book::getId, Function.identity()));

        return orderItems.stream()
                .map(item -> {
                    Book book = bookMap.get(item.getBookId());
                    if (book == null) {
                        throw new IllegalArgumentException("이런 책 없다: " + item.getBookId());
                    }

                    return item.toOrderItem(book, orders);
                })
                .collect(Collectors.toList());
    }
}
