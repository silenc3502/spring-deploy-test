package com.example.springdeploytest.cart.controller;

import com.example.springdeploytest.cart.controller.request_form.CreateCartRequestForm;
import com.example.springdeploytest.cart.controller.response_form.CreateCartResponseForm;
import com.example.springdeploytest.cart.entity.Cart;
import com.example.springdeploytest.cart.service.CartService;
import com.example.springdeploytest.cart.service.request.CreateCartRequest;
import com.example.springdeploytest.cart.service.response.CreateCartResponse;
import com.example.springdeploytest.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    final private RedisCacheService redisCacheService;
    final private CartService cartService;

    @PostMapping("/create")
    public CreateCartResponseForm createCart(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CreateCartRequestForm requestForm) {

        log.info("createCart() -> requestForm: {}", requestForm);

        String userToken = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        CreateCartRequest request = requestForm.toCreateCartRequest();
        CreateCartResponse response = cartService.create(request, accountId);

        return CreateCartResponseForm.from(response);
    }
}
