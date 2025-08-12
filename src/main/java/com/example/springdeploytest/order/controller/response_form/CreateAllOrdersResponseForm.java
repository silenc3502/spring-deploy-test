package com.example.springdeploytest.order.controller.response_form;

import com.example.springdeploytest.order.service.response.CreateAllOrdersResponse;
import com.example.springdeploytest.order.service.response.CreateOrderItemResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CreateAllOrdersResponseForm {
    final private Long ordersId;
    final private Integer itemCount;
    final private List<CreateOrderItemResponse> itemList;

    public static CreateAllOrdersResponseForm from(final CreateAllOrdersResponse response) {
        return new CreateAllOrdersResponseForm(
                response.getOrdersId(),
                response.getItemCount(),
                response.getItemList());
    }
}
