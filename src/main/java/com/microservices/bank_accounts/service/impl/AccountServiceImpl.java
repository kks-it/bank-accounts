package com.microservices.bank_accounts.service.impl;

import com.microservices.bank_accounts.constants.AccountConstants;
import com.microservices.bank_accounts.dto.AccountDto;
import com.microservices.bank_accounts.dto.CustomerDto;
import com.microservices.bank_accounts.entity.AccountEntity;
import com.microservices.bank_accounts.entity.CustomerEntity;
import com.microservices.bank_accounts.exception.CustomerAlreadyExistsException;
import com.microservices.bank_accounts.exception.ResourceNotFoundException;
import com.microservices.bank_accounts.mapper.AccountMapper;
import com.microservices.bank_accounts.mapper.CustomerMapper;
import com.microservices.bank_accounts.repository.AccountRepository;
import com.microservices.bank_accounts.repository.CustomerRepository;
import com.microservices.bank_accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements IAccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    /**
     * @param customerDto
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<CustomerEntity> existingCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(existingCustomer.isPresent()){
            throw new CustomerAlreadyExistsException(String.format("Customer already registered with given mobileNumber", customerDto.getMobileNumber()));
        }

        CustomerEntity customerEntity = new CustomerEntity();
        CustomerMapper.mapCustomerDtoToCustomerEntity(customerDto, customerEntity);
        customerEntity.setCreatedAt(LocalDateTime.now());
        customerEntity.setCreatedBy("Anonymous");
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        accountRepository.save(createNewAccount(savedCustomer));
    }

    private AccountEntity createNewAccount(CustomerEntity customer){
        AccountEntity account = new AccountEntity();

        long account_number = 1000000000L + new Random().nextLong(900000000);
        account.setNumber(account_number);
        account.setType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setCustomerId(customer.getId());
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");

        return account;
    }


    public CustomerDto getAccountDetails(String mobileNumber){
        CustomerEntity customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
        CustomerDto customerDto = new CustomerDto();
        CustomerMapper.mapCustomerEntityToCustomerDto(customer, customerDto);

       AccountEntity account = accountRepository.findByCustomerId(customer.getId())
               .orElseThrow(() -> new ResourceNotFoundException("account", "customer id", customer.getId().toString()));

       AccountDto accountDto = new AccountDto();
       AccountMapper.mapAccountEntityToAccountDto(account, accountDto);

        customerDto.setAccounts(accountDto);

        return customerDto;
    }

    /**
     * @param customerDto
     * @return
     */
    @Override
    public CustomerDto updateAccount(CustomerDto customerDto) {
        Long accountNumber = customerDto.getAccounts().getAccountNumber();

        AccountEntity accountEntity = accountRepository.findById(accountNumber)
                .orElseThrow(()-> new ResourceNotFoundException("account", "account number", accountNumber.toString()));
        AccountMapper.mapAccountDtoToAccountEntity(customerDto.getAccounts(), accountEntity);

        CustomerEntity customerEntity = customerRepository.findById(accountEntity.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("customer", "customer id", accountEntity.getCustomerId().toString()));
        CustomerMapper.mapCustomerDtoToCustomerEntity(customerDto, customerEntity);

        AccountEntity updatedAccountDetails = accountRepository.save(accountEntity);
        CustomerEntity updatedCustomerDetails = customerRepository.save(customerEntity);

        CustomerDto updatedCustomerDto = new CustomerDto();
        CustomerMapper.mapCustomerEntityToCustomerDto(updatedCustomerDetails, updatedCustomerDto);

        AccountDto updatedAccountDto = new AccountDto();
        updatedCustomerDto.setAccounts(AccountMapper.mapAccountEntityToAccountDto(updatedAccountDetails, updatedAccountDto));

        return updatedCustomerDto;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
       CustomerEntity customerEntity = customerRepository.findByMobileNumber(mobileNumber)
               .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber));

        customerRepository.deleteById(customerEntity.getId());
        accountRepository.deleteByCustomerId(customerEntity.getId());

        return true;
    }
}
