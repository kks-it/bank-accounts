package com.microservices.bank_accounts.controller;

import com.microservices.bank_accounts.constants.AccountConstants;
import com.microservices.bank_accounts.dto.CustomerDto;
import com.microservices.bank_accounts.dto.ResponseDto;
import com.microservices.bank_accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path="/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE} )
public class AccountsController {

    private IAccountService accountService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber){
        CustomerDto customerAccountDetails = accountService.getAccountDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerAccountDetails);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<CustomerDto> updateAccount(@RequestBody CustomerDto customerDto){
        System.out.println("request came here");
        CustomerDto updatedCustomerDto = accountService.updateAccount(customerDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomerDto);
    }
}
