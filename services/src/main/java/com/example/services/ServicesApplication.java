package com.example.services;

import com.example.services.model.*;

import com.example.services.service.CartItemService;
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
	CommandLineRunner run(SupplierService supplierService, Cart cart, CartItemService cartItemService, CategoryService categoryService, ProductService productService) {
		return args -> {
			Product product1=new Product("sfarjel","mdjfjbdsfbdsb",10,100L,"hh");
			Product product2=new Product("tout","mdjfjbdsfbdsb",10,2L,"hh");
			CartItem cartItem1=new CartItem(product1,5);
			//productService.saveProduct(product1);

			//cart.addCartItem(new Product("tout","mdjfjbdsfbdsb",10,1,2L,"hh"));
			//cart.addCartItem(cartItem1);
			cart.addCartItem(new CartItem(new Product("karmous","mdjfjbdsfbdsb",10,100L,"hh"),1));
			cart.getCartItems();
			cart.calCartTotal();




			//cartItemService.saveCartItem(new CartItem(new Product("sfarjl","mdjfjbdsfbdsb",10,2,100L,"hh"),5,5));
			//cartItemService.saveCartItem(new CartItem(new Product("tout","mdjfjbdsfbdsb",10,2L,"hh"),5));			//productService.saveProduct(new Product("sfarjel","mdjfjbdsfbdsb",10,100L,"hh"));

			//productService.saveProduct(new Product("sfarjel","mdjfjbdsfbdsb",10,1,100L,"hh"));
			//productService.saveProduct(new Product("tout","mdjfjbdsfbdsb",10,1,2L,"hh"));
/*


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
