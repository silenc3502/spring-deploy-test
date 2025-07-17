package com.example.springdeploytest.kakao_authentication.service;

import com.example.springdeploytest.kakao_authentication.repository.KakaoAuthenticationRepository;
import com.example.springdeploytest.kakao_authentication.service.response.KakaoUserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoAuthenticationServiceImpl implements KakaoAuthenticationService {
    final private KakaoAuthenticationRepository kakaoAuthenticationRepository;

    @Override
    public KakaoUserInfoResponse handleLogin(String code) {
        // Map<String, Object> getAccessToken(String code);
        Map<String, Object> tokenResponse = kakaoAuthenticationRepository.getAccessToken(code);
        String accessToken = tokenResponse.get("access_token").toString();

        // Map<String, Object> getUserInfo(String accessToken);
        Map<String, Object> userInfoResponse = kakaoAuthenticationRepository.getUserInfo(accessToken);
        Map<?, ?> userInfoProperties = (Map<?, ?>) userInfoResponse.get("properties");
        String nickname = (String) (userInfoProperties).get("nickname");

        Map<?, ?> userInfoKakaoAccount = (Map<?, ?>) userInfoResponse.get("kakao_account");
        String email = (String) (userInfoKakaoAccount).get("email");

        return KakaoUserInfoResponse.from(email, nickname, accessToken);
    }
}
