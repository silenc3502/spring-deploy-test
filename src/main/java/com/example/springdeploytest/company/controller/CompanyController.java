package com.example.springdeploytest.company.controller;

import com.example.springdeploytest.company.controller.request_form.RegisterCompanyRequestForm;
import com.example.springdeploytest.company.controller.response_form.RegisterCompanyResponseForm;
import com.example.springdeploytest.company.service.CompanyService;
import com.example.springdeploytest.company.service.request.RegisterCompanyRequest;
import com.example.springdeploytest.company.service.response.RegisterCompanyResponse;
import com.example.springdeploytest.order.service.OrdersService;
import com.example.springdeploytest.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {

    final private CompanyService companyService;
    final private RedisCacheService redisCacheService;

    @PostMapping("/register")
    public RegisterCompanyResponseForm registerCompany(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody RegisterCompanyRequestForm requestForm) {

        log.info("registerCompany()");

        String userToken = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        RegisterCompanyRequest request = requestForm.toRegisterCompanyRequest(accountId);
        RegisterCompanyResponse response = companyService.register(request);

        return RegisterCompanyResponseForm.from(response);
    }
}
