package com.microservices.bank_accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;

@Entity @Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity extends BaseEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String mobileNumber;
}
