package com.example.services.service;

import com.example.services.model.Category;

import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getCategories();
    Category getCategory(Long id);
    void addProductToCategory(String category, String productName);
    Category updateCategory(Category server);
    Boolean deleteCategory(Long id);
}
