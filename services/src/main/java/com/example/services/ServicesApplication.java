package com.example.services;

import com.example.services.domain.Category;
import com.example.services.domain.Image;
import com.example.services.domain.Product;
import com.example.services.service.CategoryService;
import com.example.services.service.ProductService;
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
	CommandLineRunner run(CategoryService categoryService, ProductService productService){
		return args -> {
			/*productService.saveProduct(new Product("Zitouna","mdjfjbdsfbdsb", new ArrayList<>(), 50L,10.0,new ArrayList<>()));
			productService.saveProduct(new Product("sfarjel","mdjfjbdsfbdsb",new ArrayList<>(),100L,10.0,new ArrayList<>()));
			productService.saveProduct(new Product("sfeneriya","mdjfjbdsfbdsb",new ArrayList<>(),200L,10.0,new ArrayList<>()));
			productService.saveProduct(new Product("bourdgen","mdjfjbdsfbdsb",new ArrayList<>(),150L,10.0,new ArrayList<>()));

			productService.saveImage(new Image("hhhhh"));
			productService.saveImage(new Image("ggggg"));
			productService.saveImage(new Image("fffff"));
			productService.saveImage(new Image("rrrrr"));*/


			categoryService.saveCategory(new Category("arbre", new ArrayList<>()));
			categoryService.saveCategory(new Category("plante", new ArrayList<>()) );
			categoryService.saveCategory(new Category("terre", new ArrayList<>()));
			categoryService.saveCategory(new Category("marin", new ArrayList<>()));

			/*categoryService.addProductToCategory("arbre", "Zitouna");
			categoryService.addProductToCategory("plante", "Zitouna");
			categoryService.addProductToCategory("plante", "sfarjel");
			categoryService.addProductToCategory("plante", "sfeneriya");
			categoryService.addProductToCategory("arbre", "bourdgen");
			categoryService.addProductToCategory("plante", "bourdgen");

			productService.addImageToProduct("hhhhh","Zitouna");
			productService.addImageToProduct("ggggg","Zitouna");
			productService.addImageToProduct("rrrrr","sfarjel");
			productService.addImageToProduct("rrrrr","bourdgen");*/






		};
	}

}
