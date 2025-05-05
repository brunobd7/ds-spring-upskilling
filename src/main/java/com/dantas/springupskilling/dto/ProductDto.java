package com.dantas.springupskilling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record ProductDto(
        Long id,
        @Size(min = 3, max = 80 , message = "Product name must be between 3 and 80 characters")
        @NotBlank
        String name,
        @Size(min = 10, message = "Product description must have at least 10 characters")
        @NotBlank
        String description,
        @Positive
        Double price,
        String imgUrl) implements Serializable {
}