package com.example.springdeploytest.order.service.request;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.order.entity.Orders;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateAllOrdersRequest {

    private final Long accountId;

    public Orders toOrders(Account account) {
        return new Orders(account);
    }
}
