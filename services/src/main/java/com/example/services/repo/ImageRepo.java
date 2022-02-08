package com.example.services.repo;

import com.example.services.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
    Image findImageByPath(String path);
}
