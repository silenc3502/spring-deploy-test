package com.example.springdeploytest.cart.service;

import com.example.springdeploytest.cart.service.request.CreateCartRequest;
import com.example.springdeploytest.cart.service.response.CreateCartResponse;

public interface CartService {
    CreateCartResponse create(CreateCartRequest request, Long accountId);
}
