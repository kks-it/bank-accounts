package com.microservices.bank_accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Account microservice REST API documentation",
                description = "Banking application: Accounts microservice REST API documentation",
                version = "v1",
                contact = @Contact(
                        name = "Krishna",
                        email = "id.krishnasingh@gmail.com",
                        url = "https://www.linkedin.com/in/krishna-kumar-singh-081424203/"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        )
)
public class BankAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountsApplication.class, args);
    }

}
