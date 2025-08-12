package com.example.springdeploytest.book.service;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.account.repository.AccountRepository;
import com.example.springdeploytest.book.entity.Book;
import com.example.springdeploytest.book.repository.BookRepository;
import com.example.springdeploytest.book.service.request.ListBookRequest;
import com.example.springdeploytest.book.service.request.RegisterBookRequest;
import com.example.springdeploytest.book.service.response.ListBookResponse;
import com.example.springdeploytest.book.service.response.RegisterBookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    final private BookRepository bookRepository;
    final private AccountRepository accountRepository;

    @Override
    public RegisterBookResponse register(RegisterBookRequest request, Long accountId) {
        // findById로 사용자 찾기 (등록자)
        Optional<Account> maybeAccount = accountRepository.findById(accountId);
        if (maybeAccount.isEmpty()) {
            log.info("요청한 account 존재하지 않음");
            return null;
        }

        Account account = maybeAccount.get();
        Book requestedBook = request.toBook(account);

        Book savedBook = bookRepository.save(requestedBook);
        return RegisterBookResponse.from(savedBook);
    }

    @Override
    public ListBookResponse list(ListBookRequest request) {
        // PageRequest 생성 (시작은 0부터, 화면에 보여줄 땐 0이 좀 요상하니 1이라서 1 빼야함)
        PageRequest pageRequest = PageRequest.of(
                request.getPage() - 1,
                request.getPerPage());

        // 페이지 처리된 항목
        Page<Book> paginatedBook = bookRepository.findAllWithAccount(pageRequest);

        return ListBookResponse.from(paginatedBook);
    }
}
