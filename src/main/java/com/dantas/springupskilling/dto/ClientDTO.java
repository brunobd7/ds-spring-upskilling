package com.dantas.springupskilling.dto;

import com.dantas.springupskilling.entity.Client;

import java.time.LocalDate;

public record ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {

    public Client toEntity(){
        return new Client(this);
    }

}
