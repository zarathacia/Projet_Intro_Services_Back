package com.example.services.repository;

import com.example.services.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByCategory(String category);
}
