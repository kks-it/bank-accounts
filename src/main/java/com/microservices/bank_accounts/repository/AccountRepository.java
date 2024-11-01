package com.microservices.bank_accounts.repository;

import com.microservices.bank_accounts.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByCustomerId(Long customerId);
}
