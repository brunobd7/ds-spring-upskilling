package com.dantas.springupskilling.resource;


import com.dantas.springupskilling.dto.ProductDto;
import com.dantas.springupskilling.entity.Product;
import com.dantas.springupskilling.exception.DatabaseException;
import com.dantas.springupskilling.exception.ResourceNotFoundException;
import com.dantas.springupskilling.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        Product result = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

        // MODEL MAPPER DIDN'T WORK WITH JAVA RECORDS YET
//        return product
//                .map(value -> modelMapper.map(value, ProductDto.class))
//                .orElse(null);
        return new ProductDto(result.getId(), result.getName(), result.getDescription(), result.getPrice(), result.getImgUrl());
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
        try {
            Product product = repository.getReferenceById(id); // REFERENCE DID NOT HIT THE DATABASE. PREPARE THE OBJECT AND ASSING TO JPA MONITORING SCOPE.
            BeanUtils.copyProperties(inputDto, product, "id");
            product = repository.save(product);
            return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
        }catch (EntityNotFoundException | FatalBeanException exception){ // FatalBeanException was add to handle with a call of copyProperties method on this scenario.
            throw new ResourceNotFoundException("Product not found for this id ! ");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteProduct(Long id){

        if(!repository.existsById(id))
            throw new ResourceNotFoundException("Product not found for this id :: " + id);

        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException exception) {
            throw new DatabaseException("Integrity violation occurred while deleting product with id :: " + id);
        }
    }
}
