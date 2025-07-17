package com.example.springdeploytest.kakao_authentication.controller;

import com.example.springdeploytest.kakao_authentication.controller.response_form.KakaoUserInfoResponseForm;
import com.example.springdeploytest.kakao_authentication.service.KakaoAuthenticationService;
import com.example.springdeploytest.kakao_authentication.service.response.KakaoUserInfoResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao-authentication")
public class KakaoAuthenticationController {

    final private KakaoAuthenticationService kakaoAuthenticationService;

    @GetMapping("/login")
    public KakaoUserInfoResponseForm requestLogin(@RequestParam("code") String code) throws IOException {

        log.info("requestLogin(): code {}", code);

        KakaoUserInfoResponse response = kakaoAuthenticationService.handleLogin(code);
        return KakaoUserInfoResponseForm.from(response);
    }
}
