package com.example.services.service.implementation;

import com.example.services.domain.Category;
import com.example.services.domain.Image;
import com.example.services.domain.Product;
import com.example.services.repo.ImageRepo;
import com.example.services.repo.ProductRepo;
import com.example.services.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImp implements ProductService {
    final ProductRepo productRepo;
    final ImageRepo imageRepo;

    @Override
    public Product saveProduct(Product product) {
        log.info("Saving new product {} to the database",product.getName());
        return productRepo.save(product);

    }

    @Override
    public Image saveImage(Image image) {
        log.info("Saving new image {} to the database",image.getPath());
        return imageRepo.save(image);    }


    @Override
    public List<Product> getProducts() {
        log.info("Fetching all products ");
        return productRepo.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        log.info("Fetching products by {} ",id);
        return productRepo.getById(id);
    }

    @Override
    public void addImageToProduct(String path, String productName) {
        log.info("Adding image {} to product {}",path, productName);
        Image image = imageRepo.findImageByPath(path);
        Product product = productRepo.findByName(productName);
        product.getImages().add(image);

    }

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
