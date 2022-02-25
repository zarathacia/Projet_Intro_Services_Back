package com.example.services.service.implementation;

import com.example.services.model.Product;
import com.example.services.repository.ProductRepo;
import com.example.services.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImp implements ProductService {
    final ProductRepo productRepo;

    @Override
    public Product saveProduct(Product product) {
        log.info("Saving new product {} to the database",product.getName());
        return productRepo.save(product);

    }

    @Override
    public List<Product> getProducts() {
        log.info("Fetching all products ");
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        log.info("Fetching products by {} ",id);
        return productRepo.getById(id);
    }
    /*@Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }*/

    @Override
    public Product updateProduct(Product product) {
        log.info("Updating product : {}", product.getName());
        return productRepo.save(product);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        log.info("Deleting product by ID : {}", id);
        productRepo.deleteById(id);
        return TRUE;
    }
}
