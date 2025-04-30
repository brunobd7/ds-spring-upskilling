package com.dantas.springupskilling.dto;

import java.io.Serializable;

public record ProductDto(Long id, String name, String description, Double price,
                         String imgUrl) implements Serializable {
}