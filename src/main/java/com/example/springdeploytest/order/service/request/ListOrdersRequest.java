package com.example.springdeploytest.order.service.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListOrdersRequest {
    final private Integer page;
    final private Integer perPage;
    final private Long accountId;
}
