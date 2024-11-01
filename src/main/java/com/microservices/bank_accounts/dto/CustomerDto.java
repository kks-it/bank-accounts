package com.microservices.bank_accounts.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDto {
    private String name;

    private  String email;

    private String mobileNumber;

    private List<AccountDto> accounts;
}
