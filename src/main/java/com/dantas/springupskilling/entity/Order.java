package com.dantas.springupskilling.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

    private Integer code;
    private Double basic;
    private Double discount;

}
