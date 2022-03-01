package com.example.services.service.implementation;

import com.example.services.model.Cart;
import com.example.services.model.CartItem;
import com.example.services.model.Product;
import com.example.services.repository.CartItemRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.services.service.CartItemService;

import javax.transaction.Transactional;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CartItemServiceImp implements CartItemService {
    final CartItemRepo cartItemRepo;
    final Cart cart;
    @Override
    public Boolean deleteCartItem(Long id) {
        log.info("Deleting product by ID : {}", id);
        cartItemRepo.deleteById(id);
        return TRUE;
    }

    @Override
    public List<CartItem> getCartItems() {
        log.info("Fetching all products ");
        return cartItemRepo.findAll();

    }


    @Override
    public CartItem addCartItem(CartItem cartItem) {
        log.info("Saving new product {} to the database",cartItem.getProduct());
        log.info("add Item to cart with productId: "+cartItem.getId()+", qty: "+cartItem.getQuantity()+", total: "+cartItem.getProduct().getPrice());
        Double price = cartItem.getProduct().getPrice();
        cartItem.getProduct().setPrice(cartItem.getQuantity() * price);
        System.out.println("add done???");
        cart.getCartItems().add(cartItem);
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
