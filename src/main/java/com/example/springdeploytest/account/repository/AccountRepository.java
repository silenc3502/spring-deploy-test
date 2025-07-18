package com.example.springdeploytest.account.repository;

import com.example.springdeploytest.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
