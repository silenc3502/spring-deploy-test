package com.example.springdeploytest.cart.controller.request_form;

import com.example.springdeploytest.cart.service.request.CreateCartRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCartRequestForm {
    final private Long bookId;
    final private Long quantity;

    public CreateCartRequest toCreateCartRequest() {
        return new CreateCartRequest(bookId, quantity);
    }
}
