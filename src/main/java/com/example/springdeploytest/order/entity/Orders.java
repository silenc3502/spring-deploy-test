package com.example.springdeploytest.order.entity;

import com.example.springdeploytest.account.entity.Account;
import com.example.springdeploytest.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

// 다중 주문
// 주문 내역 <- 주문 하나 (Orders) 내부에 여러 개의 주문 항목(OrderItem)이 배치됨
// Orders의 경우 이 주문이 누구의 것인지 알면됨 (Account)
// 주문 일자를 토대로 언제 주문이 되었고 배송이 지연되는지 여부를 파악할 수도 있음.

// OrderItem의 경우엔 수량, 항목(bookId), 가격, 어디의 주문인지 여부 (ordersId)

@Entity
@Getter
@NoArgsConstructor
public class Orders {
    // 일단 mysql에 order 라는 쿼리 예약어가 존재함.
    // 그렇기 때문에 orders 로 만들어지게 구성하여 예약어를 회피해야함.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @CreationTimestamp
    private LocalDateTime created;

    public Orders(Account account) {
        this.account = account;
    }
}
