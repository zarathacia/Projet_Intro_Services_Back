package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;
import com.example.services.model.Product;
import com.example.services.model.Response;
import com.example.services.service.implementation.ProductServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Api(tags = {SwaggerConfig.API_TAG2})
public class ProductResource {
    final ProductServiceImp productService;

    @ApiOperation(value = "Get all available products", notes = "Retrieve a list of all products", response = Product.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The list of products retrieved"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @GetMapping("/getall")
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok().body(productService.getProducts());
    }



    @ApiOperation(value = "Add a new product", notes = "Add a new information into the system", response = Product.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The product saved Successfully"),
            @ApiResponse(responseCode = "500", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@ApiParam(value = "Product to be saved", required = true) @RequestBody Product product){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/1/product/save").toUriString());
        return ResponseEntity.created(uri).body(productService.saveProduct(product));
    }


    @ApiOperation(value = "Find a product by its id", notes = "Retrieve a product by passing the product id", response = Product.class)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The product was retired successfully"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> getCategory(@ApiParam(value = "product id")@PathVariable("id") Long id){
        return ResponseEntity.ok().body(productService.getProduct(id));
    }


    @ApiOperation(value = "Delete a product by its id", notes = "Delete a product by passing in the product id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The product was deleted successfully"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteProduct(@ApiParam(value = "product id")@PathVariable("id" ) Long id)  {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();

    }

    @ApiOperation(value = "Update an existing product", notes = "Update a product by passing in the product information with an existing product id", response = Product.class)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The category was updated successfully"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @PutMapping("/update")
    public ResponseEntity<Product> updateInvoice(@ApiParam(value = "product object in Json format")@RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(product);
        return ResponseEntity.ok().body(updatedProduct);
    }

}

