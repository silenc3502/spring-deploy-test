package com.example.springdeploytest.order.service;

import com.example.springdeploytest.order.service.request.CreateOrdersRequest;
import com.example.springdeploytest.order.service.response.CreateOrdersResponse;

public interface OrdersService {
    CreateOrdersResponse create(CreateOrdersRequest request);
}
