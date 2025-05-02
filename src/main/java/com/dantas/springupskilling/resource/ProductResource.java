package com.dantas.springupskilling.resource;

import com.dantas.springupskilling.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public Page<ProductDto> getProducts(Pageable pageable) {
        return service.getProducts(pageable);
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto inputDto){
        return service.saveProduct(inputDto);
    }
}
