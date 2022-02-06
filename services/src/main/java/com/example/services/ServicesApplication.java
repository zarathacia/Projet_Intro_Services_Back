package com.example.services;

import com.example.services.domain.Category;
import com.example.services.domain.Product;
import com.example.services.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collection;

@SpringBootApplication
public class ServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CategoryService categoryService){
		return args -> {
			categoryService.saveProduct(new Product("Zitouna","mdjfjbdsfbdsb", new ArrayList<>(), 50L,10.0,new ArrayList<>()));
			categoryService.saveProduct(new Product("sfarjel","mdjfjbdsfbdsb",new ArrayList<>(),100L,10.0,new ArrayList<>()));
			categoryService.saveProduct(new Product("sfeneriya","mdjfjbdsfbdsb",new ArrayList<>(),200L,10.0,new ArrayList<>()));
			categoryService.saveProduct(new Product("bourdgen","mdjfjbdsfbdsb",new ArrayList<>(),150L,10.0,new ArrayList<>()));

			categoryService.saveCategory(new Category("arbre", new ArrayList<>()));
			categoryService.saveCategory(new Category("plante", new ArrayList<>()) );
			categoryService.saveCategory(new Category("terre", new ArrayList<>()));
			categoryService.saveCategory(new Category("marin", new ArrayList<>()));

			categoryService.addProductToCategory("arbre", "Zitouna");
			categoryService.addProductToCategory("plante", "Zitouna");
			categoryService.addProductToCategory("plante", "sfarjel");
			categoryService.addProductToCategory("plante", "sfeneriya");
			categoryService.addProductToCategory("arbre", "bourdgen");
			categoryService.addProductToCategory("plante", "bourdgen");


		};
	}

}
