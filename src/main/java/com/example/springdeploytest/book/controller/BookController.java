package com.example.springdeploytest.book.controller;

import com.example.springdeploytest.book.controller.request_form.RegisterBookRequestForm;
import com.example.springdeploytest.book.controller.response_form.RegisterBookResponseForm;
import com.example.springdeploytest.book.service.BookService;
import com.example.springdeploytest.book.service.request.RegisterBookRequest;
import com.example.springdeploytest.book.service.response.RegisterBookResponse;
import com.example.springdeploytest.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    final private BookService bookService;
    final private RedisCacheService redisCacheService;

    // 책 등록
    // 요청 URI -> /book/register
    // 인증 헤더 -> @RequestHeader
    // 요청 정보 -> @RequestBody
    @PostMapping("/register")
    public RegisterBookResponseForm registerBook(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody RegisterBookRequestForm requestForm) {

        log.info("registerBook() -> requestForm: {}", requestForm);

        // 인증 정보 체크 ("Bearer " 떼어내기)
        String userToken = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        // 요청 정보를 가지고 Book 엔티티 구성
        RegisterBookRequest request = requestForm.toRegisterBookRequest();
        RegisterBookResponse response = bookService.register(request, accountId);

        // 책 등록이 잘 되었는지 read 혹은 필요에 따라 list에 적합한 응답 구성
        return RegisterBookResponseForm.from(response);
    }
}
