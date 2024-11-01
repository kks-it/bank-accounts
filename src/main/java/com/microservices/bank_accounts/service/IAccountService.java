package com.microservices.bank_accounts.service;

import com.microservices.bank_accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto getAccountDetails(String mobileNumber);

    CustomerDto updateAccount(CustomerDto customerDto);
}
