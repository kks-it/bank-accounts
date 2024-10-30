package com.microservices.bank_accounts.entity;

import jakarta.persistence.Entity;
import lombok.*;

@Entity @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
public class AccountEntity extends BaseEntity{
    private Long number;
    private Long customerId;
    private String type;
    private String branchAddress;
}

