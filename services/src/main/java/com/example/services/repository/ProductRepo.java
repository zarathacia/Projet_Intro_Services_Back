package com.example.services.repository;

import com.example.services.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);
    Product getById(Long id);
}
