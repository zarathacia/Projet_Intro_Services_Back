package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;
import com.example.services.model.Category;
import com.example.services.model.Response;
import com.example.services.service.implementation.CategoryServiceImp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Api(tags = {SwaggerConfig.API_TAG1})
public class CategoryResource {
    final private CategoryServiceImp categoryService;

    @ApiOperation(value = "Add a new category", notes = "Add a new information into the system", response = Category.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The category saved Successfully"),
            @ApiResponse(responseCode = "500", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @PostMapping("/category/save")
    public ResponseEntity<Category> saveCategory(@ApiParam(value = "Category to be saved", required = true) @RequestBody Category category){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/category/save").toUriString());
        return ResponseEntity.created(uri).body(categoryService.saveCategory(category));
    }

    @ApiOperation(value = "Get all available categories", notes = "Retrieve a list of all categories", response = Category.class)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The list of categories retrieved"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok().body(categoryService.getCategories());
    }


    @ApiOperation(value = "Find an category by its id", notes = "Retrieve an category by passing the category id", response = Category.class)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The category was retired successfully"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @GetMapping("getByCategory/{id}")
    public ResponseEntity<Category> getCategory(@ApiParam(value = "category id")@PathVariable("id") Long id){
        return ResponseEntity.ok().body(categoryService.getCategory(id));
    }



    @ApiOperation(value = "Update an existing category", notes = "Update a category by passing in the category information with an existing category id", response = Category.class)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The category was updated successfully"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @PutMapping("/update")
    public ResponseEntity<Category> updateInvoice(@ApiParam(value = "category object in Json format")@RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @ApiOperation(value = "Delete a category by its id", notes = "Delete an category by passing in the category id")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The category was deleted successfully"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCategory(@ApiParam(value = "category id")@PathVariable("id" ) Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("deleted" , categoryService.deleteCategory(id)))
                        .message("Category deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }

    @ApiOperation(value = "Add product to category", notes = "Add a product to category by passing information")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "The invoice was deleted successfully"),
            @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
            @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
    })
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
