package com.example.services.service;

import com.example.services.exception.NotEnoughProductsInStockException;
import com.example.services.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    void checkout() throws NotEnoughProductsInStockException;

    Product getProduct(Long id);

    BigDecimal getTotal();
}