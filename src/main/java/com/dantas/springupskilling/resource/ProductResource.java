package com.dantas.springupskilling.resource;

import com.dantas.springupskilling.dto.ProductDto;
import com.dantas.springupskilling.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService service;

    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return service.getProductById(id);
    }
}
