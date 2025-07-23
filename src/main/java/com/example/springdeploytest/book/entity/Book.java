package com.example.springdeploytest.book.entity;

import com.example.springdeploytest.account.entity.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 여기서 무엇을 관리하고 싶냐에 따라 구성이 달라짐.
    // 이 부분은 전적으로 작성하거나 개발하거나 혹은 의뢰한 사람에 따라 구성이 달라짐.
    // 여기서 책의 제목과 내용, 그리고 책을 등록한 사용자를 관리하겠다는 뜻임.
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Book(String title, String content, Account account) {
        this.title = title;
        this.content = content;
        this.account = account;
    }
}
