package com.example.services.repo;

import com.example.services.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByCategory(String category);
}
