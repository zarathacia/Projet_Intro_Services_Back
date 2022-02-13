/*package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;
import com.example.services.exception.NotEnoughProductsInStockException;
import com.example.services.model.Product;
import com.example.services.repository.ProductRepo;
import com.example.services.service.CartService;
import com.example.services.service.ProductService;
import com.example.services.service.implementation.ProductServiceImp;
import com.example.services.service.implementation.CartServiceImp;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static com.sun.org.apache.xml.internal.serializer.Version.getProduct;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.API_TAG4})

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

    @GetMapping("/Cart/addProduct/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        getProduct(productId).ifPresent(cartService::addProduct);
        return cart();
    }



    @GetMapping("/Cart/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
        getProduct(productId).ifPresent(cartService::removeProduct);
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
*/