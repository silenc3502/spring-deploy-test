package com.example.springdeploytest.cart.service.response;

import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.cart.entity.Cart;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCartResponse {
    final private Long cartId;
    final private Long bookId;
    final private String bookTitle;
    final private Long quantity;

    public static CreateCartResponse from(final Cart cart) {
        Book responseBook = cart.getBook();

        return new CreateCartResponse(
                cart.getId(),
                responseBook.getId(),
                responseBook.getTitle(),
                cart.getQuantity()
        );
    }
}
