package com.dantas.springupskilling.exception.handler;

import com.dantas.springupskilling.dto.CustomError;
import com.dantas.springupskilling.dto.ValidationError;
import com.dantas.springupskilling.exception.DatabaseException;
import com.dantas.springupskilling.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomError(Instant.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> handleDatabase(Exception ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CustomError(Instant.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValid(MethodArgumentNotValidException argumentNotValidException, HttpServletRequest request) {

        ValidationError validationError = new ValidationError(
                Instant.now(),
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Request body contain(s) invalid data!",
                request.getRequestURI());

        argumentNotValidException.getBindingResult().getFieldErrors()
                .forEach(fieldError -> validationError.addError(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(validationError);
    }
}
