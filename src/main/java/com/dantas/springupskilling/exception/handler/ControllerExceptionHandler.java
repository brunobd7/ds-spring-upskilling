package com.dantas.springupskilling.exception.handler;

import com.dantas.springupskilling.dto.CustomError;
import com.dantas.springupskilling.exception.DatabaseException;
import com.dantas.springupskilling.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> handleException(ResourceNotFoundException ex, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new CustomError(Instant.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> handleException(Exception ex, HttpServletRequest request){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body( new CustomError(Instant.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI()));
    }
}
