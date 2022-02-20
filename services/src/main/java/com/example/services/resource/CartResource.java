package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;
import com.example.services.model.CartItem;
import com.example.services.model.Product;
import com.example.services.service.CartService;
import com.example.services.service.implementation.ProductServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = { SwaggerConfig.API_TAG4 })

public class CartResource {

    private final CartService cartService;

    private final ProductServiceImp productService;

    @GetMapping("/Cart")
    public ModelAndView cart() {
        ModelAndView modelAndView = new ModelAndView("/Cart");
        modelAndView.addObject("products", cartService.getProductsInCart());
        modelAndView.addObject("total", cartService.getTotal().toString());
        return modelAndView;
    }

    /*
     * 5aleha hedhi matfasa5hech
     * 
     * @GetMapping("/Cart/addProduct/{productId}")
     * public ModelAndView addProductToCart(@PathVariable("productId") Long
     * productId) {
     * getProduct(productId).ifPresent(cartService::addProductToCart);
     * return cart();
     * }
     */

    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<CartItem>> getCartItem(@ApiParam(value = "id")@PathVariable("id") Long id){
        return ResponseEntity.ok().body(cartService.getCartItem(id));
    }

    @PostMapping("/Cart/addProduct/{productId}")
    public ResponseEntity<?> addProductToCart(@RequestBody ProductToCart form) {
        cartService.addProductToCart(form.getProduct());
        return ResponseEntity.ok().build();
    };


      @GetMapping("/Cart/removeCartItem/{itemId}")
      public ModelAndView removeItemFromCart(@PathVariable("itemId") Long
      Id) {
          if ((cartService.getProductsInCart()).get(getCartItem(Id)) == 1) {
              (cartService.getProductsInCart()).remove(getCartItem(Id));
          }
          return cart();
     // getCartItem(Id).ifPresent(cartService::removeCartItem);return cart();
      }



    /*
     * @GetMapping("/Cart/checkout")
     * public ModelAndView checkout() {
     * try {
     * cartService.checkout();
     * } catch (NotEnoughProductsInStockException e) {
     * return cart().addObject("outOfStockMessage", e.getMessage());
     * }
     * return cart();
     * }
     */
}

@Data
class ProductToCart {
    private Product product;

}