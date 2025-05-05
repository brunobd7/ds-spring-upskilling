package com.dantas.springupskilling.resource;

import com.dantas.springupskilling.dto.ProductDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private final ProductService service;

    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(Pageable pageable) {
        return ResponseEntity.ok(service.getProducts(pageable));
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@Valid @RequestBody ProductDto inputDto){
        ProductDto savedProduct = service.saveProduct(inputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.id())
                .toUri();
        return ResponseEntity.created(uri).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id, @Valid @RequestBody ProductDto inputDto) {
        return ResponseEntity.ok(service.updateProduct(id,inputDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
