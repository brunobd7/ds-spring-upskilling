package com.dantas.springupskilling.dto;

import com.dantas.springupskilling.projection.CustomerInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CustomerInfoDTO {

    public String name;

    public CustomerInfoDTO(CustomerInfo customerInfo) {
        this.name = customerInfo.getName();
    }

    @Override
    public String toString() {
        return "CustomerInfoDTO [name=" + name + "]";
    }
}
