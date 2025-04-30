package com.dantas.springupskilling.repository;

import com.dantas.springupskilling.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
