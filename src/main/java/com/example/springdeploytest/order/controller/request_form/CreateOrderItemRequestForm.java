package com.example.springdeploytest.order.controller.request_form;

import com.example.springdeploytest.order.service.request.CreateOrderItemRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrderItemRequestForm {
    final private Long bookId;
    final private Long quantity;
    final private Long price;

    public CreateOrderItemRequest toCreateOrderItemRequest() {
        return new CreateOrderItemRequest(bookId, quantity, price);
    }
}
