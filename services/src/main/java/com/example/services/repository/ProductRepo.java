package com.example.services.repository;

import com.example.services.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Optional<Product> getById(Long id);


}
