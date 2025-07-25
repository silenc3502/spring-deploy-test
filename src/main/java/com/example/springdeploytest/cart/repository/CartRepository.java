package com.example.springdeploytest.cart.repository;

import com.example.springdeploytest.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
