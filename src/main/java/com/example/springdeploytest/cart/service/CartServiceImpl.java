package com.example.springdeploytest.cart.service;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.account.repository.AccountRepository;
import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.book.repository.BookRepository;
import com.example.springdeploytest.cart.entity.Cart;
import com.example.springdeploytest.cart.repository.CartRepository;
import com.example.springdeploytest.cart.service.request.CreateCartRequest;
import com.example.springdeploytest.cart.service.response.CreateCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    final private CartRepository cartRepository;
    final private AccountRepository accountRepository;
    final private BookRepository bookRepository;

    @Override
    public CreateCartResponse create(CreateCartRequest request, Long accountId) {
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if (maybeAccount.isEmpty()) {
            return null;
        }
        Account account = maybeAccount.get();

        Long bookId = request.getBookId();
        Optional<Book> maybeBook = bookRepository.findById(bookId);
        if (maybeBook.isEmpty()) {
            return null;
        }
        Book book = maybeBook.get();

        Cart requestedCart = request.toCart(book, account);
        Cart savedCart = cartRepository.save(requestedCart);

        return CreateCartResponse.from(savedCart);
    }
}
