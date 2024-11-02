package com.microservices.bank_accounts.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

//@Validated
@Data
public class CustomerDto {
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(max = 30)
    private String name;

    @Email(message = "Email address should be a valid value")
    @NotEmpty(message = "Name cannot be null or empty")
    private  String email;

    @Pattern(regexp = "($^|[0-9]{10})", message = "Mobile number must be 10 digits ")
    @NotEmpty(message = "Mobile number cannot be null or empty")
    private String mobileNumber;

    private AccountDto accounts;
}
