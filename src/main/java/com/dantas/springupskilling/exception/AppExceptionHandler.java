package com.dantas.springupskilling.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, exception.getMessage());

        problemDetail.setTitle("Resource not found");
        problemDetail.setProperty("uri", request.getRequestURI());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(DatabaseException.class)
    public ProblemDetail handleDatabaseException(DatabaseException exception, HttpServletRequest request){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());

        problemDetail.setTitle("Error occurs on database operation");
        problemDetail.setProperty("uri", request.getRequestURI());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){

        ProblemDetail problemDetail = ProblemDetail
                .forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());

        problemDetail.setTitle("Validation error on request body");
        problemDetail.setProperty("uri", request.getRequestURI());
        problemDetail.setProperty("timestamp", Instant.now());

        problemDetail.setProperty("errorsList", exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> Map.of("field", fieldError.getField(), "errorMessage", fieldError.getDefaultMessage()))
        );

        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(HttpMessageNotReadableException exception, HttpServletRequest request){

        ProblemDetail problemDetail = ProblemDetail.
                forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());

        problemDetail.setTitle("Invalid request body");
        problemDetail.setProperty("uri", request.getRequestURI());
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }
}
