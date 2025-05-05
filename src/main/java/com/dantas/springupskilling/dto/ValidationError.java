package com.dantas.springupskilling.dto;

import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ValidationError extends CustomError {

    List<FieldMessage> validationErrors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public void addError(String fieldName, String message) {
        validationErrors.add(new FieldMessage(fieldName, message));
    }
}
