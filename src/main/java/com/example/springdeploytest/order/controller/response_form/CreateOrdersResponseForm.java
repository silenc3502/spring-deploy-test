package com.example.springdeploytest.order.controller.response_form;

import com.example.springdeploytest.order.service.response.CreateOrdersResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateOrdersResponseForm {
    final private Long bookId;
    final private String bookTitle;
    final private Long quantity;
    final private Long price;

    public static CreateOrdersResponseForm from(final CreateOrdersResponse response) {
        return new CreateOrdersResponseForm(
                response.getBookId(),
                response.getBookTitle(),
                response.getQuantity(),
                response.getPrice());
    }
}
