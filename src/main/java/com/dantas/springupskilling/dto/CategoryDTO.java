package com.dantas.springupskilling.dto;


import com.dantas.springupskilling.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;


    public CategoryDTO(Category entityCategory) {
        this.id = entityCategory.getId();
        this.name = entityCategory.getName();
    }

}
