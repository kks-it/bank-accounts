package com.microservices.bank_accounts.controller;

import com.microservices.bank_accounts.constants.AccountConstants;
import com.microservices.bank_accounts.dto.CustomerDto;
import com.microservices.bank_accounts.dto.ResponseDto;
import com.microservices.bank_accounts.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path="/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE} )
@Validated
public class AccountsController {

    private IAccountService accountService;

    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam
                     @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
                     String mobileNumber){
        CustomerDto customerAccountDetails = accountService.getAccountDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerAccountDetails);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<CustomerDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){
        CustomerDto updatedCustomerDto = accountService.updateAccount(customerDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomerDto);
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
                String mobileNumber){
        boolean isTrue = accountService.deleteAccount(mobileNumber);

        if(isTrue){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
