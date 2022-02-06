package com.example.services.resource;

import com.example.services.domain.Category;
import com.example.services.domain.Product;
import com.example.services.service.implementation.CategoryServiceImp;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryResource {
    final private CategoryServiceImp categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }

    @PostMapping("/category/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/category/save").toUriString());
        return ResponseEntity.created(uri).body(categoryService.saveCategory(category));
    }

    @PostMapping("/product/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/product/save").toUriString());
        return ResponseEntity.created(uri).body(categoryService.saveProduct(product));
    }

    @PostMapping("/product/addtocategory")
    public ResponseEntity<?> addProductToCategory(@RequestBody ProductToCategory form){
        categoryService.addProductToCategory(form.getCategory(), form.getProductName());
        return ResponseEntity.ok().build();
    };

}


@Data
class ProductToCategory{
    private String category;
    private String productName;
}
