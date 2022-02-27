package com.example.services.service.implementation;

import com.example.services.model.CartItem;
import com.example.services.repository.CartItemRepo;
import com.example.services.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.services.service.CartItemService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartItemServiceImp implements CartItemService {
    final CartItemRepo cartItemRepo;

    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        log.info("Saving new product {} to the database",cartItem.getProduct());
        return cartItemRepo.save(cartItem);
    }
/*    @Override
    public void addProductToCartItem(Long Id, String productName) {
        log.info("Adding product {} to category {}", productName, Id);
        CartItem cartItem = cartItemRepo.findById(Id);
        Product product = productRepo.findByName(productName);
        Id.getProducts().add(product);
    }*/
}
