package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;
import com.example.services.model.Cart;
import com.example.services.model.CartItem;
import com.example.services.model.Product;
import com.example.services.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
@Slf4j

@RestController
@RequestMapping("/api/cart")
//@RequiredArgsConstructor
@Api(tags = { SwaggerConfig.API_TAG4 })
public class CartResource {
    private ProductService productService;

    private Cart cart;

    @Autowired
    public CartResource(Cart cart,ProductService productService) {
        this.cart = cart; this.productService = productService;
    }

    @GetMapping
    public String cartResource(Authentication authentication, Model model){
//        if (authentication!=null){
//            UserDetail userDetails = (UserDetail) authentication.getPrincipal();
//            model.addAttribute("user",userDetails.getUser());
//        }

        List<CartItem> cartItems = cart.getCartItems();
        if (cartItems.size()>0){
            model.addAttribute("cartItems",cartItems);
            model.addAttribute("total",cart.calCartTotal());
        }
        return "template/user/page/product/cart";

    }
    @GetMapping("getAll")
    public List<CartItem> getAllCartItem(){
        log.info("getAll Cart Items ->>"+cart.getCartItems().size());
        return cart.getCartItems() ;
    }

    @GetMapping("/get")
    public CartItem getCartItemByProductId(
            @RequestParam(name = "productId", required = true) Long productId
    ){
        log.info("Get CartItem wit ProductId: "+productId);
        Product product = productService.getProduct(productId);
        CartItem item = new CartItem(
                product,1
        );
        return item;
    }

    @PostMapping("/update")
    public List<CartItem> updateCartItem(
            HttpServletResponse response, HttpServletRequest request,
            @RequestBody List<CartItem> cartItems
    ){
        log.info("update cart items with items number: "+cartItems.size());
        if (cartItems!=null){
            cart.clearCartItem();
            cartItems.forEach(item->cart.addCartItem(item));
        }else{
            cart.clearCartItem();
        }
        return cart.getCartItems();
    }




}
/*
public class CartResource {

    private final CartService cartService;

    private final ProductService productService;
    @Autowired
    public CartResource(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
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

/*
      @GetMapping("/Cart/removeCartItem/{itemId}")
      public ModelAndView removeCartItem(@PathVariable("itemId") Long
      Id) {
          /*if ((cartService.getProductsInCart()).get(getCartItem(Id)) == 1) {
              (cartService.getProductsInCart()).remove(getCartItem(Id));
          }
          return cart();*/
   /*  productService.getProduct(Id).ifPresent(cartService::removeCartItem);
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

}*/