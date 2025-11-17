package com.dantas.springupskilling.dto;

import com.dantas.springupskilling.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private Set<String> roles = new HashSet<>();


    public UserDTO(User userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.phone = userEntity.getPhone();
        this.birthDate = userEntity.getBirthDate();

        userEntity.getRoles().forEach(role -> this.roles.add(role.getAuthority()));
    }
}
