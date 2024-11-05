package com.microservices.bank_accounts.controller;

import com.microservices.bank_accounts.constants.AccountConstants;
import com.microservices.bank_accounts.dto.CustomerDto;
import com.microservices.bank_accounts.dto.ErrorDto;
import com.microservices.bank_accounts.dto.ResponseDto;
import com.microservices.bank_accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Accounts CRUD REST APIs",
        description = "Account controller to create, read, update and delete accounts and customer details"
)
@AllArgsConstructor
@RestController
@RequestMapping(path = "/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountsController {

    private IAccountService accountService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer &  Account in Bank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    }
    )
    @PostMapping(path = "/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorDto.class)
                    )
            )
    }
    )
    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam
                                                         @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
                                                         String mobileNumber) {
        CustomerDto customerAccountDetails = accountService.getAccountDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerAccountDetails);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @PutMapping(path = "/update")
    public ResponseEntity<CustomerDto> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = accountService.updateAccount(customerDto);

        return ResponseEntity.status(HttpStatus.OK).body(updatedCustomerDto);
    }

    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status Internal Server error"
                    )
            }
    )
    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                     @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
                                                     String mobileNumber) {
        boolean isTrue = accountService.deleteAccount(mobileNumber);

        if (isTrue) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
