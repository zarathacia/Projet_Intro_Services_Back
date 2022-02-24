package com.example.services;

import com.example.services.model.Category;
import com.example.services.model.Product;
import com.example.services.model.Supplier;
import com.example.services.service.CartService;
import com.example.services.service.CategoryService;
import com.example.services.service.ProductService;
import com.example.services.service.SupplierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicesApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

	/*@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}*/
	@Bean
	CommandLineRunner run(SupplierService supplierService, CartService cartservice, CategoryService categoryService, ProductService productService) {
		return args -> {
			/*productService.saveProduct(new Product("Zitouna","mdjfjbdsfbdsb", new ArrayList<>(), 50L, new BigDecimal("1.5"),new ArrayList<>()));
			productService.saveProduct(new Product("sfarjel","mdjfjbdsfbdsb",new ArrayList<>(),100L,new BigDecimal("15"),new ArrayList<>()));
			productService.saveProduct(new Product("bourdgen","mdjfjbdsfbdsb",new ArrayList<>(),150L,new BigDecimal("10"),new ArrayList<>()));



			categoryService.saveCategory(new Category("arbre", new ArrayList<>()));
			categoryService.saveCategory(new Category("plante", new ArrayList<>()));
			categoryService.saveCategory(new Category("terre", new ArrayList<>()));
			categoryService.saveCategory(new Category("marin", new ArrayList<>()));

			categoryService.addProductToCategory("arbre", "Zitouna");

			supplierService.saveSupplier(new Supplier( "ayyedi", new ArrayList<>()));
			supplierService.addProductToSupplier("ayyedi","Zitouna");*/
			//cartservice.addProductToCart(new Product("Zit","mdjfjdsb", new ArrayList<>(), 50L, new BigDecimal("1.5"),new ArrayList<>()));;
			//cartservice.getTotal();
			//cartservice.getCartItems();
			/*
			productService.addImageToProduct("hhhhh","Zitouna");
			productService.addImageToProduct("ggggg","Zitouna");
			productService.addImageToProduct("rrrrr","sfarjel");
			productService.addImageToProduct("rrrrr","bourdgen");
			productService.addImageToProduct("rrrrr","bourdgen");*/
		};

	}


	}
