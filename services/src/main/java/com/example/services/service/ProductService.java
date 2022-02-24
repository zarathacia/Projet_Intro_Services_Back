package com.example.services.service;

import com.example.services.model.Image;
import com.example.services.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Image saveImage(Image image);
    List<Product> getProducts();
    Optional<Product> getProduct(Long id);
    //Optional<Product> findById(Long id);
    void addImageToProduct(String path, String productName);
    Product updateProduct(Product server);
    Boolean deleteProduct(Long id);
}
