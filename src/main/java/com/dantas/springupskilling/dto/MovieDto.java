package com.dantas.springupskilling.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.dantas.springupskilling.entity.Movie}
 */
public record MovieDto(Long id, String name) implements Serializable {
}