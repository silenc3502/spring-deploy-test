package com.example.springdeploytest.order.service;

import com.example.springdeploytest.order.service.request.CreateAllOrderItemRequest;
import com.example.springdeploytest.order.service.request.CreateAllOrdersRequest;
import com.example.springdeploytest.order.service.request.ListOrdersRequest;
import com.example.springdeploytest.order.service.response.CreateAllOrdersResponse;
import com.example.springdeploytest.order.service.response.ListOrdersResponse;

public interface OrdersService {
    CreateAllOrdersResponse createAll(
            CreateAllOrdersRequest ordersRequest,
            CreateAllOrderItemRequest orderItemRequest);

    ListOrdersResponse list(ListOrdersRequest request);
}
