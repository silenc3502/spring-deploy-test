package com.example.springdeploytest.book.controller.request_form;

import com.example.springdeploytest.book.service.request.RegisterBookRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class RegisterBookRequestForm {
    // 여러분들이 지정한 요청 정보
    final private String title;
    final private String content;
    
    // RequestForm을 Request로 변환
    public RegisterBookRequest toRegisterBookRequest() {
        return new RegisterBookRequest(title, content);
    }
}
