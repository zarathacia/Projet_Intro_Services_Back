package com.example.services.service;

import com.example.services.model.CartItem;
import com.example.services.model.Product;

import java.util.List;

public interface CartItemService {

    List<CartItem> getCartItems();
    CartItem addCartItem(CartItem cartItem);
    Boolean deleteCartItem(Long id);
    //CartItem  updateCartItem(CartItem cartItem);
}
