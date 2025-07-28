//package com.example.springdeploytest.order.service.response;
//
//import com.example.springdeploytest.book.entity.Book;
//import com.example.springdeploytest.order.entity.Orders;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//@Getter
//@RequiredArgsConstructor
//public class CreateOrdersResponse {
//    final private Long bookId;
//    final private String bookTitle;
//    final private Long quantity;
//    final private Long price;
//
//    public static CreateOrdersResponse from(final Orders orders) {
//        Book orderedBook = orders.getBook();
//
//        return new CreateOrdersResponse(
//                orderedBook.getId(),
//                orderedBook.getTitle(),
//                orders.getQuantity(),
//                orders.getPrice());
//    }
//}
