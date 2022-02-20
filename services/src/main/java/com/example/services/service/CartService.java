package com.example.services.service;

import com.example.services.exception.NotEnoughProductsInStockException;
import com.example.services.model.CartItem;
import com.example.services.model.Product;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface CartService {

    void addProductToCart(Product product);

    Map<Product, Integer> getProductsInCart();

    void removeCartItem(CartItem cartItem);

    Map<Product, Integer> getCartItems();

    //void checkout() throws NotEnoughProductsInStockException;

    Optional<CartItem> getCartItem(Long id);

    BigDecimal getTotal();
}