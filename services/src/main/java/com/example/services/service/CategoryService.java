package com.example.services.service;

import com.example.services.domain.Category;
import com.example.services.domain.Product;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getCategories();
    Category getCategory(Long id);
    Product saveProduct(Product product);
    void addProductToCategory(String category, String productName);
    Category updateCategory(Category server);
    Boolean deleteCategory(Long id);
}
