package com.example.springdeploytest.order.controller.request_form;

import com.example.springdeploytest.order.service.request.CreateAllOrderItemRequest;
import com.example.springdeploytest.order.service.request.CreateAllOrdersRequest;
import com.example.springdeploytest.order.service.request.CreateOrderItemRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class CreateAllOrdersRequestForm {
    final private List<CreateOrderItemRequestForm> orderItems;

    public CreateAllOrdersRequest toCreateAllOrdersRequest(Long accountId) {
        return new CreateAllOrdersRequest(accountId);
    }

    public CreateAllOrderItemRequest toCreateAllOrderItemRequest() {
        List<CreateOrderItemRequest> mapped = orderItems.stream()
                .map(CreateOrderItemRequestForm::toCreateOrderItemRequest)
                .collect(Collectors.toList());

        return new CreateAllOrderItemRequest(mapped);
    }
}
