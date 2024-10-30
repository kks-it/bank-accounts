package com.microservices.bank_accounts.service.impl;

import com.microservices.bank_accounts.dto.CustomerDto;
import com.microservices.bank_accounts.entity.CustomerEntity;
import com.microservices.bank_accounts.mapper.CustomerMapper;
import com.microservices.bank_accounts.repository.AccountRepository;
import com.microservices.bank_accounts.repository.CustomerRepository;
import com.microservices.bank_accounts.service.IAccountService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerMapper.mapToCustomerEntity(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);


    }
}
