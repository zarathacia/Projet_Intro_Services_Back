package com.example.services.service.implementation;

import com.example.services.exception.NotEnoughProductsInStockException;
import com.example.services.model.CartItem;
import com.example.services.model.Product;
import com.example.services.repository.CartItemRepo;
import com.example.services.repository.ProductRepo;
import com.example.services.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@Slf4j
@AllArgsConstructor
public class CartServiceImp implements CartService {

    private final ProductRepo productRepo;
    private final CartItemRepo cartItemRepo;

    private Map<Product, Integer> cartItems = new HashMap<Product, Integer>();
    private Map<Product, Integer> products = new HashMap<>();



    @Override
    public void addProductToCart(Product product) {
        if (cartItems.containsKey(product)) {
            cartItems.replace(product, cartItems.get(product) + 1);
        } else {
            cartItems.put(product, 1);
        }
    }



    @Override
    public Map<Product, Integer> getProductsInCart() {
        return null;
    }

    @Override
    public void removeCartItem(CartItem cartItem) {
        //if (products.containsKey(product)) {
            if (cartItems.get(cartItem) > 1)
                cartItems.replace(cartItem, cartItems.get(cartItem) - 1);
            else if (cartItems.get(cartItem) == 1) {
                cartItems.remove(cartItem);
            }
        //}
    }

    @Override
    public Map<Product, Integer> getCartItems() {
        return Collections.unmodifiableMap(cartItems);
    }

    /*@Override
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepo.findOne(entry.getKey().getId());
            if (product.getStock() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setStock(product.getStock() - entry.getValue());
        }
        productRepo.save(products.keySet());
        productRepo.flush();
        products.clear();
    }*/

    @Override
    public Optional<CartItem> getCartItem(Long id) {
        log.info("Fetching products by {} ",id);
        return cartItemRepo.findById(id);
    }

    /*@Override
    public double getTotal() {
        return cartItems.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(double.valueOf(entry.getValue())))
                .reduce(double::add)
                .orElse(double.ZERO);
    }*/
}
