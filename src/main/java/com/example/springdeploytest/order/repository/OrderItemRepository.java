package com.example.springdeploytest.order.repository;

import com.example.springdeploytest.order.entity.OrderItem;
import com.example.springdeploytest.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrdersIn(Collection<Orders> orders);
}
