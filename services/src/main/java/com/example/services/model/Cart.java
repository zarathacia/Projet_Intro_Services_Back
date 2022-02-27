package com.example.services.model;

import com.example.services.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private List<CartItem> cartItems = new ArrayList<>();
    private Double cartTotal;
    private ProductService productService;

    @Autowired
    public Cart(ProductService productService) {
        this.productService = productService;
    }
//cartimp
    public void addCartItem(CartItem cartItem){
        log.info("add Item to cart with prouctId: "+cartItem.getId()+", qty: "+cartItem.getQuantity()+", total: "+cartItem.getProduct().getPrice());
        Double price = cartItem.getProduct().getPrice();
        cartItem.getProduct().setPrice(cartItem.getQuantity() * price);

        this.cartItems.add(cartItem);
        //this.cartItems.saveAll();
        System.out.println("add done???");

    }
    public void updateCartItem(CartItem cartItem){
        log.info("update Item in cart");
        int duplicate = 0;
        for (CartItem item: cartItems) {
            if (item.getId() == cartItem.getId()){
                (item.getProduct()).setStock((cartItem.getProduct()).getStock());
                item.setQuantity((cartItem).getQuantity());
                Double price = productService.getProduct(cartItem.getId()).getPrice();
                (item.getProduct()).setPrice((cartItem.getQuantity()*price));
                duplicate += 1;
            }
        }

        if (duplicate == 0){
            addCartItem(cartItem);
        }
    }
    public void removeCartItem(CartItem cartItem){
        log.info("remove Item in cart");
        int deleteIndex = -1;
        for (int i = 0; i <cartItems.size() ; i++) {
            if (cartItems.get(i).getId()== cartItem.getId()){
                deleteIndex = i;
                break;
            }
        }
        if (deleteIndex != -1){
            cartItems.remove(deleteIndex);
        }
    }
    public void clearCartItem(){
        log.info("clear Item in cart");
        if (this.cartItems !=null){
            this.cartItems.clear();
        }
    }
    public Double calCartTotal(){
        log.info("get total price Item to cart");
        this.cartTotal = cartItems.stream().mapToDouble(p->p.getProduct().getPrice()).sum();
        System.out.println(this.cartTotal);
        return this.cartTotal;
    }

    public List<CartItem> getCartItems() {
        System.out.println(this.cartItems);
        return new ArrayList<>(this.cartItems);

    }
}
