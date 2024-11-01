package com.microservices.bank_accounts.mapper;

import com.microservices.bank_accounts.dto.AccountDto;
import com.microservices.bank_accounts.entity.AccountEntity;

public class AccountMapper {
    public static AccountDto mapAccountEntityToAccountDto(AccountEntity accEntity, AccountDto accDto){
        accDto.setAccountNumber(accEntity.getNumber());
        accDto.setAccountType(accEntity.getType());
        accDto.setBranchAddress(accEntity.getBranchAddress());

        return accDto;
    }

    public static AccountEntity mapAccountDtoToAccountEntity(AccountDto accDto, AccountEntity accEntity){
        accEntity.setNumber(accDto.getAccountNumber());
        accEntity.setType(accDto.getAccountType());
        accEntity.setBranchAddress(accDto.getBranchAddress());

        return accEntity;
    }
}
