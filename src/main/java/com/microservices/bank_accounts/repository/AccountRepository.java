package com.microservices.bank_accounts.repository;

import com.microservices.bank_accounts.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
