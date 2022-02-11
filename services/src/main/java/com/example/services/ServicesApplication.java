package com.example.services;

import com.example.services.model.Category;
import com.example.services.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class ServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CategoryService categoryService) {
		return args -> {
			categoryService.saveCategory(new Category("zitouna", new ArrayList<>()));
			categoryService.saveCategory(new Category("sfarjel", new ArrayList<>()));


		};
	}
}