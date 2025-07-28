//package com.example.springdeploytest.order.service.request;
//
//import com.example.springdeploytest.account.entity.Account;
//import com.example.springdeploytest.book.entity.Book;
//import com.example.springdeploytest.order.entity.Orders;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//@Getter
//@RequiredArgsConstructor
//public class CreateOrdersRequest {
//    final private Long bookId;
//    final private Long quantity;
//    final private Long price;
//    final private Long accountId;
//
//    public Orders toOrders(Book book, Account account) {
//        return new Orders(book, account, quantity, price);
//    }
//}
