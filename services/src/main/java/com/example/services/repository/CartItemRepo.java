package com.example.services.repository;


import com.example.services.model.Cart;
import com.example.services.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
    CartItem findById (long Id);
}
