package com.dantas.springupskilling.dto;

import com.dantas.springupskilling.entities.Product;
import lombok.Data;

@Data
public class ProductMinDTO {

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDTO(Product productEntity) {
        this.id = productEntity.getId();
        this.name = productEntity.getName();
        this.price = productEntity.getPrice();
        this.imgUrl = productEntity.getImgUrl();
    }

}
