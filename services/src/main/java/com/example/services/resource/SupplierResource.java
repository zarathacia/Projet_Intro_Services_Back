package com.example.services.resource;

import com.example.services.constant.SwaggerConfig;
import com.example.services.model.Response;
import com.example.services.model.Supplier;
import com.example.services.service.implementation.SupplierServiceImp;
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
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = { SwaggerConfig.API_TAG3 })
public class SupplierResource {final private SupplierServiceImp supplierService;

        @ApiOperation(value = "Add a new supplier", notes = "Add a new information into the system", response = Supplier.class)
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "The supplier saved Successfully"),
                @ApiResponse(responseCode = "500", description = "Successfully retrieved list"),
                @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
                @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
                @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
                @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
        })
        @PostMapping("/supplier/save")
        public ResponseEntity<Supplier> saveSupplier(
                @ApiParam(value = "Supplier to be saved", required = true) @RequestBody Supplier supplier) {
            URI uri = URI.create(
                    ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/supplier/save").toUriString());
            return ResponseEntity.created(uri).body(supplierService.saveSupplier(supplier));
        }

    @ApiOperation(value = "Find a supplier by its id", notes = "Retrieve a supplier by passing the supplier id", response = Supplier.class)
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "The list of suppliers retrieved"),
                @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
                @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
                @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
                @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
                @ApiResponse(responseCode = "401", description = "You don't have permission to this resource"),
                @ApiResponse(responseCode = "200", description = "The supplier was retired successfully"),
                @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
                @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
                @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
                @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
                @ApiResponse(responseCode = "401", description = "You don't have permission to this resource") })
        @GetMapping("getBySupplier/{id}")
        public ResponseEntity<Supplier> getSupplier(@ApiParam(value = "supplier id") @PathVariable("id") Long id) {
            return ResponseEntity.ok().body(supplierService.getSupplier(id));
        }

        @ApiOperation(value = "Update an existing supplier", notes = "Update a supplier by passing in the supplier information with an existing supplier id", response = Supplier.class)
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "The supplier was updated successfully"),
                @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
                @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
                @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
                @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
                @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
        })
        @PutMapping("/supplier/update")
        public ResponseEntity<Supplier> updateSupplier(
                @ApiParam(value = "supplier object in Json format") @RequestBody Supplier supplier) {
            Supplier updatedsupplier = supplierService.updateSupplier(supplier);
            return ResponseEntity.ok().body(updatedsupplier);
        }

        @ApiOperation(value = "Delete a supplier by its id", notes = "Delete a supplier by passing in the supplier id")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "The supplier was deleted successfully"),
                @ApiResponse(responseCode = "400", description = "The request is malformed or invalid"),
                @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
                @ApiResponse(responseCode = "500", description = "An internal server error occurred"),
                @ApiResponse(responseCode = "403", description = "You are not authorized. Please authenticate and try again"),
                @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")
        })
        @DeleteMapping("/supplier/delete/{id}")
        public ResponseEntity<Response> deleteSupplier(@ApiParam(value = "supplier id") @PathVariable("id") Long id) {
            supplierService.deleteSupplier(id);
            return ResponseEntity.ok().build();

        }
    


      @ApiOperation(value = "Add product to supplier", notes =
      "Add a product to supplier by passing information")

      @ApiResponses({@ApiResponse(responseCode = "200", description =
      "The invoice was deleted successfully"),

      @ApiResponse(responseCode = "400", description =
      "The request is malformed or invalid"),

      @ApiResponse(responseCode = "404", description =
      "The resource URL was not found on the server"),

      @ApiResponse(responseCode = "500", description =
      "An internal server error occurred"),

      @ApiResponse(responseCode = "403", description =
      "You are not authorized. Please authenticate and try again"),

      @ApiResponse(responseCode = "401", description =
      "You don't have permission to this resource")
      })



      @PostMapping("/product/addtosupplier")
      public ResponseEntity<?> addProductToSupplier(@RequestBody
                                                            com.example.services.resource.ProductToSupplier form) {

          supplierService.addProductToSupplier(form.getSupplierName(),
                  form.getSupplierName());
          return ResponseEntity.ok().build();
      }

      }



      @Data
      class ProductToSupplier {
        private String supplierName;
        private String productName;
      }



