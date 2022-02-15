package com.example.services.repository;


import com.example.services.model.Cart;
import com.example.services.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
    Cart findByCartItem (CartItem cartItem);
}
