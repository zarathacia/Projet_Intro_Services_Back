package com.example.services.resource;

import com.example.services.model.Cart;
import com.example.services.model.CartItem;
import com.example.services.model.Product;
import com.example.services.model.Response;
import com.example.services.service.implementation.CartItemServiceImp;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cart_item")
@RequiredArgsConstructor
public class CartItemResource {
    final CartItemServiceImp cartItemService;
    final Cart cart;
    /*@PostMapping("/product/addtocart")
    public ResponseEntity<?> addProductToCartItem(@RequestBody ProductToCartItem form){
        cartItemService.addProductToCartItem(form.getId(), form.getProductName());
        return ResponseEntity.ok().build();
    };*/
    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@ApiParam(value = "Product to be saved", required = true) @RequestBody CartItem cartItem){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/1/product/save").toUriString());

        return ResponseEntity.created(uri).body(cartItemService.addCartItem(cartItem));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCartItem(@ApiParam(value = "cart_item id")@PathVariable("id" ) Long id)  {
        cartItemService.deleteCartItem(id);
        return ResponseEntity.ok().build();

    }
    @GetMapping("/getall")
    public ResponseEntity<List<CartItem>> getCartItems(){
        return ResponseEntity.ok().body(cart.getCartItems());
    }
    @PutMapping("/update")
    public ResponseEntity<CartItem> updateCartItem(@ApiParam(value = "cartItem object in Json format")@RequestBody CartItem cartItem) {
        CartItem updatedCartItem = cartItemService.updateCartItem(cartItem);
        return ResponseEntity.ok().body(updatedCartItem);
    }
    @GetMapping("/getTotal")
    public ResponseEntity<Double> getTotal() {

        return ResponseEntity.ok().body(cart.calCartTotal());
    }
}/*
@Data
class ProductToCartItem{
    private Long id;
    private String productName;
}*/