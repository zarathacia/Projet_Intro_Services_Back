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
    public CartItem updateCartItem(CartItem cartItem) {
        log.info("Updating product : {}", cartItem.getProduct().getName());
        return cartItemRepo.save(cartItem);
    }

    @Override
    public double getTotal() {
        log.info("get total price Item to cart");
        
        System.out.println(getCartItems().stream().mapToDouble(p->p.getTotalPrice()).sum());

        return getCartItems().stream().mapToDouble(p->p.getTotalPrice()).sum();
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        {
            log.info("remove Item in cart");
            int deleteIndex = -1;
            for (int i = 0; i <cart.getCartItems().size() ; i++) {
                if (cart.getCartItems().get(i).getId()== cartItem.getId()){
                    deleteIndex = i;
                    break;
                }
            }
            if (deleteIndex != -1){
                cart.getCartItems().remove(deleteIndex);
            }
        }
    }

    @Override
    public List<CartItem> getCartItems() {
        log.info("Fetching all products ");
        return cartItemRepo.findAll();

    }

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        log.info("Saving new cart item {} to the database",cartItem.getProduct());
        log.info("add Item to cart with productId: "+cartItem.getId()+", qty: "+cartItem.getQuantity()+", total: "+cartItem.getProduct().getPrice());
        Double price = cartItem.getProduct().getPrice();
        cartItem.setTotalPrice(cartItem.getQuantity() * price);
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
