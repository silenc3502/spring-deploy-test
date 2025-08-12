package com.example.springdeploytest.order.service.response;

import com.example.springdeploytest.order.controller.response_form.ListOrdersResponseForm;
import com.example.springdeploytest.order.entity.OrderItem;
import com.example.springdeploytest.order.entity.Orders;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ListOrdersResponse {
    final private List<Orders> pagedOrdersList;
    final private List<OrderItem> pagedOrderItemList;
    final private Integer totalPages;
    final private Long totalElements;

    public ListOrdersResponseForm transformToResponseForm() {
        List<Map<String, Object>> ordersSummaryList = pagedOrdersList.stream()
                .map(orders -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("orderId", orders.getId());
                    map.put("created", orders.getCreated());

                    List<OrderItem> itemsForOrder = pagedOrderItemList.stream()
                            .filter(orderItem -> orderItem.getOrders().getId().equals(orders.getId()))
                            .collect(Collectors.toList());

                    // A-B-C-D, DEF-FUNCTION, CLASS-CONSTRUCTION, MAKE-COFFEE
                    // 위와 같이 책 이름이 전부 긴 경우 아래와 같이 처리하기도 함
                    // A-B-C-D, DEF-FUNCTION 외 다수
                    // 혹은 A-B-C-D, DEF-FUNCTION ....
                    // 또는 A-B-C-D, DEF-FUNCTION 외 2건
                    String bookNameList = itemsForOrder.stream()
                            .map(item -> item.getBook().getTitle())
                            .collect(Collectors.joining(", "));

                    if (bookNameList.length() > 20) {
                        bookNameList = bookNameList.substring(0, 20) + "......";
                    }
                    map.put("orderName", bookNameList);

                    Long totalPrice = itemsForOrder.stream()
                            .mapToLong(item -> item.getPrice() * item.getQuantity())
                            .sum();
                    map.put("totalPrice", totalPrice);

                    return map;
                })
                .collect(Collectors.toList());

        return new ListOrdersResponseForm(
                ordersSummaryList,
                totalPages,
                totalElements
        );
    }

    public static ListOrdersResponse from(
            final List<Orders> pagedOrdersList,
            final List<OrderItem> pagedOrderItemList,
            final Integer totalPages,
            final Long totalElements) {

        return new ListOrdersResponse(
                pagedOrdersList,
                pagedOrderItemList,
                totalPages,
                totalElements
        );
    }
}
