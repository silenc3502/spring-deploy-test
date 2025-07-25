package com.example.springdeploytest.cart.controller.response_form;

import com.example.springdeploytest.cart.service.response.CreateCartResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCartResponseForm {
    final private Long cartId;
    final private Long bookId;
    final private String bookTitle;
    final private Long quantity;

    public static CreateCartResponseForm from(final CreateCartResponse response) {
        return new CreateCartResponseForm(
                response.getCartId(),
                response.getBookId(),
                response.getBookTitle(),
                response.getQuantity());
    }
}
