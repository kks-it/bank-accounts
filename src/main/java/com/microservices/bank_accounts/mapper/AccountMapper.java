package com.microservices.bank_accounts.mapper;

import com.microservices.bank_accounts.dto.AccountDto;
import com.microservices.bank_accounts.entity.AccountEntity;

public class AccountMapper {
    public static AccountDto mapToAccountDto(AccountEntity accEntity){
        AccountDto accDto = new AccountDto();
        accDto.setAccountNumber(accEntity.getNumber());
        accDto.setAccountType(accEntity.getType());
        accDto.setBranchAddress(accEntity.getBranchAddress());

        return accDto;
    }

    public static AccountEntity mapToAccountEntity(AccountDto accDto){
        AccountEntity accEntity = new AccountEntity();
        accEntity.setNumber(accDto.getAccountNumber());
        accEntity.setType(accDto.getAccountType());
        accEntity.setBranchAddress(accDto.getBranchAddress());

        return accEntity;
    }
}
