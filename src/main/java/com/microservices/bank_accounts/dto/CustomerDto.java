package com.microservices.bank_accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

//@Validated
@Schema(
        name = "customer",
        description = "Schema to hold customer and account information"
)
@Data
public class CustomerDto {
    @Schema(
            description = "Name of the customer",
            example = "Krishna Kumar Singh"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(max = 30)
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "id.krishnasingh@gmail.com"
    )
    @Email(message = "Email address should be a valid value")
    @NotEmpty(message = "Name cannot be null or empty")
    private  String email;

    @Schema(
            description = "Mobile number of the customer",
            example = "9876543210"
    )
    @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
    @NotEmpty(message = "Mobile number cannot be null or empty")
    private String mobileNumber;

    @Schema(
            description = "Account details of the customer"
    )
    private AccountDto accounts;
}
