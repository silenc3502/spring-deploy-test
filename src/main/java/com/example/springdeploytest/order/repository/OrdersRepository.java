package com.example.springdeploytest.order.repository;

import com.example.springdeploytest.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
