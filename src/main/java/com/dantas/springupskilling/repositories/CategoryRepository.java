package com.dantas.springupskilling.repositories;

import com.dantas.springupskilling.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
