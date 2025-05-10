package com.dantas.springupskilling.dto;

import com.dantas.springupskilling.entity.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record ClientDTO(
        Long id,
        @NotBlank String name,
        String cpf, Double income,
        @NotNull @PastOrPresent LocalDate birthDate,
        Integer children) {

    public Client toEntity(){
        return new Client(this);
    }

}
