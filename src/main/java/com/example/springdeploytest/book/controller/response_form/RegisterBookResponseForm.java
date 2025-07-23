package com.example.springdeploytest.book.controller.response_form;

import com.example.springdeploytest.book.service.response.RegisterBookResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterBookResponseForm {
    final private Long bookId;
    final private String title;
    final private String content;
    final private String registeredAccountNickname;

    public static RegisterBookResponseForm from(RegisterBookResponse response) {
        return new RegisterBookResponseForm(
                response.getBookId(),
                response.getTitle(),
                response.getContent(),
                response.getRegisteredAccountNickname());
    }
}
