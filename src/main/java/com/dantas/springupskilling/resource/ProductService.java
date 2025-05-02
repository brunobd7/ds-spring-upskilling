package com.dantas.springupskilling.resource;


import com.dantas.springupskilling.dto.ProductDto;
import com.dantas.springupskilling.entity.Product;
import com.dantas.springupskilling.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        Optional<Product> result = repository.findById(id);
        // MODEL MAPPER DIDN'T WORK WITH JAVA RECORDS YET
//        return product
//                .map(value -> modelMapper.map(value, ProductDto.class))
//                .orElse(null);
        return result
                .map(product ->
                        new ProductDto(product.getId(), product.getName(), product.getDescription(),
                                product.getPrice(), product.getImgUrl())
                ).orElse(null);
    }

    @Transactional(readOnly = true)
    public Page<ProductDto> getProducts(Pageable pageable) {
        return repository.findAll(pageable)
                .map(product -> new ProductDto(product.getId(), product.getName(), product.getDescription(),
                        product.getPrice(), product.getImgUrl())
                );
    }

    @Transactional
    public ProductDto saveProduct(ProductDto inputDto){
        Product product = new Product();
        BeanUtils.copyProperties(inputDto, product,"id");
        product = repository.save(product);
        return new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getImgUrl());
    }

    @Transactional
    public ProductDto updateProduct(Long id , ProductDto inputDto){
        Product product = repository.getReferenceById(id); // REFERENCE DID NOT HIT THE DATABASE. PREPARE THE OBJECT AND ASSING TO JPA MONITORING SCOPE.
        BeanUtils.copyProperties(inputDto, product,"id");
        product = repository.save(product);
        return new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getImgUrl());
    }

    @Transactional
    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
}
