package com.example.springdeploytest.order.service;

import com.example.springdeploytest.order.service.request.CreateAllOrderItemRequest;
import com.example.springdeploytest.order.service.request.CreateAllOrdersRequest;
import com.example.springdeploytest.order.service.response.CreateAllOrdersResponse;

public interface OrdersService {
    CreateAllOrdersResponse createAll(
            CreateAllOrdersRequest ordersRequest,
            CreateAllOrderItemRequest orderItemRequest);
}
