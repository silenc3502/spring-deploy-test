package com.example.springdeploytest.order.controller;

import com.example.springdeploytest.order.controller.request_form.CreateOrdersRequestForm;
import com.example.springdeploytest.order.controller.response_form.CreateOrdersResponseForm;
import com.example.springdeploytest.order.service.OrdersService;
import com.example.springdeploytest.order.service.request.CreateOrdersRequest;
import com.example.springdeploytest.order.service.response.CreateOrdersResponse;
import com.example.springdeploytest.redis_cache.RedisCacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    final private OrdersService ordersService;
    final private RedisCacheService redisCacheService;

    @PostMapping("/create")
    public CreateOrdersResponseForm createOrders(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CreateOrdersRequestForm requestForm) {

        log.info("createOrders()");

        String userToken = authorizationHeader.replace("Bearer ", "").trim();
        Long accountId = redisCacheService.getValueByKey(userToken, Long.class);

        CreateOrdersRequest request = requestForm.toCreateOrdersRequest(accountId);
        CreateOrdersResponse response = ordersService.create(request);

        return CreateOrdersResponseForm.from(response);
    }
}
