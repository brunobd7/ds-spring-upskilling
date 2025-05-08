package com.dantas.springupskilling.entity;

import com.dantas.springupskilling.dto.ClientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tb_client")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private Double income;
    private LocalDate birthDate;
    private Integer children;

    public Client(ClientDTO clientDTO) {
        this.name = clientDTO.name();
        this.cpf = clientDTO.cpf();
        this.income = clientDTO.income();
        this.birthDate = clientDTO.birthDate();
        this.children = clientDTO.children();
    }

}
