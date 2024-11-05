package com.microservices.bank_accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold account information"
)
@Data
public class AccountDto {
    @Schema(
            description = "Account number of the bank account"
    )
    @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
    @NotEmpty(message = "Account number cannot be null or empty")
    private Long accountNumber;

    @Schema(
            description = "Account type of bank account",
            example = "Savings"
    )
    @NotEmpty(message = "Account number cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Branch address of the bank"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
