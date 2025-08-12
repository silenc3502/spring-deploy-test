package com.example.springdeploytest.order.service.response;

import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.order.entity.OrderItem;
import com.example.springdeploytest.order.entity.Orders;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrdersResponse {
    final private Long ordersId;
    final private Integer itemCount;
    final private List<CreateOrderItemResponse> itemList;

    public static CreateAllOrdersResponse from(
            final Orders orders,
            List<OrderItem> savedOrderItemList) {

        List<CreateOrderItemResponse> itemResponseList = savedOrderItemList.stream()
                .map(CreateOrderItemResponse::from)
                .collect(Collectors.toList());

        return new CreateAllOrdersResponse(
                orders.getId(),
                itemResponseList.size(),
                itemResponseList
        );
    }
}
