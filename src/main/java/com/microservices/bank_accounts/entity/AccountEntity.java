package com.microservices.bank_accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity @Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Table(name="accounts")
public class AccountEntity extends BaseEntity{
    @Id
    private Long number;
    private Long customerId;
    private String type;
    private String branchAddress;
}

