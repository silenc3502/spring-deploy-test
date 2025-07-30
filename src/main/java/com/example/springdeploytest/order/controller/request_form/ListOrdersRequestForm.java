package com.example.springdeploytest.order.controller.request_form;

import com.example.springdeploytest.order.service.request.ListOrdersRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class ListOrdersRequestForm {
    final private Integer page;
    final private Integer perPage;

    public ListOrdersRequest toListOrdersRequest(Long accountId) {
        return new ListOrdersRequest(page, perPage, accountId);
    }
}
