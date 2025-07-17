package com.example.springdeploytest.kakao_authentication.service;

import com.example.springdeploytest.kakao_authentication.repository.KakaoAuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoAuthenticationServiceImpl implements KakaoAuthenticationService {
    final private KakaoAuthenticationRepository kakaoAuthenticationRepository;

    @Override
    public String handleLogin(String code) {
        // Map<String, Object> getAccessToken(String code);
        Map<String, Object> tokenResponse = kakaoAuthenticationRepository.getAccessToken(code);
        return tokenResponse.get("access_token").toString();
    }
}
