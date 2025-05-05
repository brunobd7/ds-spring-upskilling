package com.dantas.springupskilling.exception.handler;

import com.dantas.springupskilling.exception.DatabaseException;
import com.dantas.springupskilling.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, 
                ex.getMessage());
        
        problemDetail.setTitle("Resource Not Found");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("uri", request.getRequestURI());
        
        return problemDetail;
    }

    @ExceptionHandler(DatabaseException.class)
    public ProblemDetail handleDatabase(DatabaseException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                ex.getMessage());
        
        problemDetail.setTitle("Database Error");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("uri", request.getRequestURI());
        
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, 
            HttpServletRequest request) {
        
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.UNPROCESSABLE_ENTITY,
                "Request body contains invalid data!");
        
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("uri", request.getRequestURI());
        
        // Add validation errors as a property
        var fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> Map.of(
                    "field", fieldError.getField(),
                    "message", fieldError.getDefaultMessage()
                ))
                .toList();
        
        problemDetail.setProperty("validationErrors", fieldErrors);
        
        return problemDetail;
    }
}