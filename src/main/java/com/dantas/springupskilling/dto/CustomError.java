package com.dantas.springupskilling.dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;

}
