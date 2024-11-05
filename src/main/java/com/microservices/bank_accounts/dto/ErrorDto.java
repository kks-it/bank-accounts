package com.microservices.bank_accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Schema(
        name = "Error response",
        description = "Schema to hold error response information"
)
@Data @AllArgsConstructor
public class ErrorDto {
    @Schema(
            description = "api path invoked by client",
            example = "/api/accounts"
    )
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened",
            example = "500"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message of the error happended",
            example = "Internal server error"
    )
    private String errorMessage;

    private LocalDateTime errorTime;
}
