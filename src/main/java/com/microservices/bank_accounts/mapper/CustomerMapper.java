package com.microservices.bank_accounts.mapper;

import com.microservices.bank_accounts.dto.CustomerDto;
import com.microservices.bank_accounts.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerEntity customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerEntity mapToCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}