package com.example.springdeploytest.account.controller;

import com.example.springdeploytest.account.controller.request_form.RegisterAccountRequestForm;
import com.example.springdeploytest.account.controller.response_form.RegisterAccountResponseForm;
import com.example.springdeploytest.account.service.AccountService;
import com.example.springdeploytest.account.service.request.RegisterAccountRequest;
import com.example.springdeploytest.account.service.response.RegisterAccountResponse;
import com.example.springdeploytest.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    final private AccountService accountService;
    final private RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterAccountResponseForm createAccount(
            @RequestHeader("Authorization") String authorizedHeader,
            @RequestBody RegisterAccountRequestForm requestForm) {

        log.info("createAccount() -> requestForm: {}", requestForm);

        // 지금 가입 요청한 사용자가 정상적인 임시 토큰을 가지고 있는가?
        String temporaryUserToken = authorizedHeader.replace("Bearer ", "").trim();
        String accessToken = redisCacheService.getValueByKey(temporaryUserToken, String.class);
        if (accessToken == null) {
            throw new IllegalArgumentException("만료되었거나 잘못된 토큰 요청입니다.");
        }

        // 회원 가입 진행
        RegisterAccountRequest request = requestForm.toRegisterAccountRequest();
        RegisterAccountResponse response = accountService.register(request);
        Long accountId = response.getAccountId();

        // 찐 토큰 발급
        String userToken = UUID.randomUUID().toString();
        redisCacheService.setKeyAndValue(userToken, accountId);
        redisCacheService.setKeyAndValue(accountId, accessToken);

        // 임시 토큰 삭제
        redisCacheService.deleteByKey(temporaryUserToken);

        return RegisterAccountResponseForm.from(response, userToken);
    }
}
