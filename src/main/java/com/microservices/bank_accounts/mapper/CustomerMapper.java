package com.microservices.bank_accounts.mapper;

import com.microservices.bank_accounts.dto.CustomerDto;
import com.microservices.bank_accounts.entity.CustomerEntity;

public class CustomerMapper {

    public static CustomerDto mapCustomerEntityToCustomerDto(CustomerEntity customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static CustomerEntity mapCustomerDtoToCustomerEntity(CustomerDto customerDto, CustomerEntity customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

}