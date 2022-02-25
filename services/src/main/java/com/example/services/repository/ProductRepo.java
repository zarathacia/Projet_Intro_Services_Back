package com.example.services.repository;

import com.example.services.model.Product;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Optional<Product> getById(Long id);
    //Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
   // Page<Product> findByNameContaining(@Param("name") String name, Pageable pageable);

}
