package com.example.springdeploytest.order.service;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.account.repository.AccountRepository;
import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.book.repository.BookRepository;
import com.example.springdeploytest.order.entity.OrderItem;
import com.example.springdeploytest.order.entity.Orders;
import com.example.springdeploytest.order.repository.OrderItemRepository;
import com.example.springdeploytest.order.repository.OrdersRepository;
import com.example.springdeploytest.order.service.request.CreateAllOrderItemRequest;
import com.example.springdeploytest.order.service.request.CreateAllOrdersRequest;
import com.example.springdeploytest.order.service.request.CreateOrderItemRequest;
import com.example.springdeploytest.order.service.response.CreateAllOrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    final private OrdersRepository ordersRepository;
    final private OrderItemRepository orderItemRepository;
    final private BookRepository bookRepository;
    final private AccountRepository accountRepository;

    @Override
    @Transactional
    public CreateAllOrdersResponse createAll(
            CreateAllOrdersRequest ordersRequest,
            CreateAllOrderItemRequest orderItemRequest) {

        Long accountId = ordersRequest.getAccountId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Orders orders = ordersRequest.toOrders(account);
        ordersRepository.save(orders);

        List<Long> bookIdList = orderItemRequest.getOrderItems().stream()
                .map(CreateOrderItemRequest::getBookId)
                .distinct()
                .collect(Collectors.toList());

        List<Book> bookList = bookRepository.findAllById(bookIdList);
        if (bookList.size() != bookIdList.size()) {
            throw new IllegalArgumentException("존재하지 않는 책이 포함되었습니다.");
        }

        List<OrderItem> orderItemList = orderItemRequest.toOrderItemList(bookList, orders);
        List<OrderItem> savedOrderItemList = orderItemRepository.saveAll(orderItemList);

        return CreateAllOrdersResponse.from(orders, savedOrderItemList);
    }
}
