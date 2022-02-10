package com.example.services.service;

import com.example.services.domain.Image;
import com.example.services.domain.Product;

import java.util.List;

public interface ProductService {
    Product saveProduct(Product product);
    Image saveImage(Image image);
    List<Product> getProducts();
    Product getProduct(Long id);
    void addImageToProduct(String path, String productName);
    Product updateProduct(Product server);
    Boolean deleteProduct(Long id);
}