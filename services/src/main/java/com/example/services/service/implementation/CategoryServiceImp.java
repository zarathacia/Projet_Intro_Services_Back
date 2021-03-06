package com.example.services.service.implementation;

import static java.lang.Boolean.TRUE;

import java.util.List;

import javax.transaction.Transactional;

import com.example.services.model.Category;
import com.example.services.model.Product;
import com.example.services.repository.CategoryRepo;
import com.example.services.repository.ProductRepo;
import com.example.services.service.CategoryService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service

@Transactional
@Slf4j
public class CategoryServiceImp implements CategoryService {
    final private CategoryRepo categoryRepo;
    final private ProductRepo productRepo;

    public CategoryServiceImp(CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Category saveCategory(Category category) {
        log.info("Saving new category : {}", category.getCategory());
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getCategories() {
        log.info("Fetching all categories ");
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategory(Long id) {

        log.info("Fetching servers by id : {} ", id);
        return categoryRepo.findById(id).get();
    }

    @Override
    public void addProductToCategory(String categoryName, String productName) {
        log.info("Adding product {} to category {}", productName, categoryName);
        Category category = categoryRepo.findByCategory(categoryName);
        Product product = productRepo.findByName(productName);
        category.getProducts().add(product);
    }

    @Override
    public Category updateCategory(Category category) {
        log.info("Updating category : {}", category.getCategory());
        return categoryRepo.save(category);
    }

    @Override
    public Boolean deleteCategory(Long id) {
        log.info("Deleting category by ID : {}", id);
        categoryRepo.deleteById(id);
        return TRUE;
    }
}
