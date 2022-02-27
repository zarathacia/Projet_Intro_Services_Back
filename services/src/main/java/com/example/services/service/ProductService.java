package com.example.services.service;

import com.example.services.model.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> getProducts();
    Product getProduct(Long id);
    //Optional<Product> findById(Long id);
    Product updateProduct(Product server);
    Boolean deleteProduct(Long id);
}
