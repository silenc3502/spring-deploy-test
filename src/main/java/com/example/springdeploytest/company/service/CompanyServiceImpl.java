package com.example.springdeploytest.company.service;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.account.repository.AccountRepository;
import com.example.springdeploytest.company.entity.Company;
import com.example.springdeploytest.company.repository.CompanyRepository;
import com.example.springdeploytest.company.service.request.RegisterCompanyRequest;
import com.example.springdeploytest.company.service.response.RegisterCompanyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    final private AccountRepository accountRepository;
    final private CompanyRepository companyRepository;

    @Override
    public RegisterCompanyResponse register(RegisterCompanyRequest request) {
        Long accountId = request.getAccountId();
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        Company requestedCompany = request.toCompany(account);
        Company savedCompany = companyRepository.save(requestedCompany);

        return RegisterCompanyResponse.from(savedCompany);
    }
}
