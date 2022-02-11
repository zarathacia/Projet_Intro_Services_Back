package com.example.services.resource;

import com.example.services.model.Image;
import com.example.services.model.Product;
import com.example.services.model.Response;
import com.example.services.service.implementation.ProductServiceImp;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/1")
@RequiredArgsConstructor
public class ProductSource {
    final ProductServiceImp productService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok().body(productService.getProducts());
    }


    @PostMapping("/product/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/1/product/save").toUriString());
        return ResponseEntity.created(uri).body(productService.saveProduct(product));
    }

    @PostMapping("/image/save")
    public ResponseEntity<Image> saveRole(@RequestBody Image image){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/1/image/save").toUriString());
        return ResponseEntity.created(uri).body(productService.saveImage(image));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(productService.getProduct(id));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProduct(@PathVariable("id" ) Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted" , productService.deleteProduct(id)))
                        .message("Product deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

    @PostMapping("/product/addtoproduct")
    public ResponseEntity<?> addImageToProduct(@RequestBody ImageToCategory form){
        productService.addImageToProduct(form.getPath(), form.getProductName());
        return ResponseEntity.ok().build();
    };

}

@Data
class ImageToCategory{
    private String path;
    private String productName;
}

