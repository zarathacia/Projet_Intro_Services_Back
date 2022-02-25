package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;

import com.example.services.exception.NotEnoughProductsInStockException;
import com.example.services.model.Cart;
import com.example.services.model.Category;
import com.example.services.model.Product;
import com.example.services.service.CartService;
import com.example.services.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/1")
//@RequiredArgsConstructor
@Api(tags = { SwaggerConfig.API_TAG4 })

public class CartResource {

    private final CartService cartService;

    private final ProductService productService;
    @Autowired
    public CartResource(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<Cart> saveCart(@ApiParam(value = "Cart to be saved", required = true) @RequestBody Cart cart){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/category/save").toUriString());
        return ResponseEntity.created(uri).body(cartService.saveCart(cart));
    }

    @GetMapping("/Cart")
    public ModelAndView cart() {
        ModelAndView modelAndView = new ModelAndView("/Cart");
        modelAndView.addObject("products", cartService.getCartItems());
        modelAndView.addObject("total", cartService.getTotal().toString());
        return modelAndView;
    }


    @GetMapping("/Cart/addProduct/{productId}")
      public ModelAndView addProductToCart(@PathVariable("Id") Long
      Id) {

      productService.getProduct(Id).ifPresent(cartService::addProductToCart);

      return cart();
      }


    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<Product>> getCartItem(@ApiParam(value = "id")@PathVariable("id") Long id){
        return ResponseEntity.ok().body(cartService.getCartItem(id));
    }

    /*@PostMapping("/Cart/addProduct/{productId}")
    public ResponseEntity<?> addProductToCart(@RequestBody ProductToCart form) {
        cartService.addProductToCart(form.getProduct());
        return ResponseEntity.ok().build();
    };*/


      @GetMapping("/Cart/removeCartItem/{itemId}")
      public ModelAndView removeCartItem(@PathVariable("itemId") Long
      Id) {
          /*if ((cartService.getProductsInCart()).get(getCartItem(Id)) == 1) {
              (cartService.getProductsInCart()).remove(getCartItem(Id));
          }
          return cart();*/
     productService.getProduct(Id).ifPresent(cartService::removeCartItem);
     return cart();

      }




      @GetMapping("/Cart/checkout")
      public ModelAndView checkout() {
      try {
      cartService.checkout();
      } catch (NotEnoughProductsInStockException e) {
      return cart().addObject("outOfStockMessage", e.getMessage());
      }
      return cart();
      }

}

@Data
class ProductToCart {
    private Product product;

}