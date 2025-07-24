package com.example.springdeploytest.account.service;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.account.repository.AccountRepository;
import com.example.springdeploytest.account.service.request.RegisterAccountRequest;
import com.example.springdeploytest.account.service.response.RegisterAccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    final private AccountRepository accountRepository;

    @Override
    public RegisterAccountResponse register(RegisterAccountRequest request) {
        Account requestedAccount = request.toAccount();
        Account savedAccount = accountRepository.save(requestedAccount);

        return RegisterAccountResponse.from(savedAccount);
    }
}
