package com.microservices.bank_accounts.exception;

import com.microservices.bank_accounts.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex, WebRequest req){
        ErrorDto errorResponse = new ErrorDto(req.getContextPath(), HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> handleResouceNotFoundException(ResourceNotFoundException ex, WebRequest req){
        ErrorDto error = new ErrorDto(req.getDescription(false), HttpStatus.NOT_FOUND, ex.getMessage() , LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
