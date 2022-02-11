package com.example.services.repository;

import com.example.services.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findImageByPath(String path);
}
