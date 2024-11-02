package com.microservices.bank_accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {
    @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
    @NotEmpty(message = "Account number cannot be null or empty")
    private Long accountNumber;

    @NotEmpty(message = "Account number cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
