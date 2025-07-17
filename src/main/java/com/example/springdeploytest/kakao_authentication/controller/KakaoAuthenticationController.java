package com.example.springdeploytest.kakao_authentication.controller;

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

    @GetMapping("/login")
    public void requestLogin(@RequestParam("code") String code,
                             HttpServletResponse response) throws IOException {

        log.info("requestLogin(): code {}", code);
    }
}
