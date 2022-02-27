package com.example.services.resource;

import com.example.services.model.CartItem;
import com.example.services.service.implementation.CartItemServiceImp;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/cart_item")
@RequiredArgsConstructor
public class CartItemResource {
    final CartItemServiceImp cartItemService;
    /*@PostMapping("/product/addtocart")
    public ResponseEntity<?> addProductToCartItem(@RequestBody ProductToCartItem form){
        cartItemService.addProductToCartItem(form.getId(), form.getProductName());
        return ResponseEntity.ok().build();
    };*/
    @PostMapping("/save")
    public ResponseEntity<CartItem> saveCartItem(@ApiParam(value = "Product to be saved", required = true) @RequestBody CartItem cartItem){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/1/product/save").toUriString());
        return ResponseEntity.created(uri).body(cartItemService.saveCartItem(cartItem));
    }

}/*
@Data
class ProductToCartItem{
    private Long id;
    private String productName;
}*/