package com.example.services.service.implementation;

import static java.lang.Boolean.TRUE;

import java.util.List;

import javax.transaction.Transactional;


import com.example.services.model.Product;
import com.example.services.model.Supplier;
import com.example.services.repository.SupplierRepo;
import com.example.services.repository.ProductRepo;
import com.example.services.service.SupplierService;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service

@Transactional
@Slf4j
public  class SupplierServiceImp implements SupplierService {
    final private SupplierRepo supplierRepo;
    final private ProductRepo productRepo;

    public SupplierServiceImp(ProductRepo productRepo, SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        log.info("Saving new supplier : {}", supplier.getSupplier());
        return supplierRepo.save(supplier);
    }

    @Override
    public List<Supplier> getSuppliers() {
        log.info("Fetching all suppliers ");
        return supplierRepo.findAll();
    }



    @Override
    public Supplier updateSupplier(Supplier supplier) {
        log.info("Updating supplier : {}", supplier.getSupplier());
        return supplierRepo.save(supplier);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        return null;
    }

    @Override
    public Boolean deleteSupplier(Long id) {
        log.info("Deleting supplier by ID : {}", id);
        supplierRepo.deleteById(id);
        return TRUE;
    }


    @Override
    public Supplier getSupplier(Long id) {

        log.info("Fetching servers by id : {} ", id);
        return supplierRepo.findById(id).get();
    }
    @Override
    public void addProductToSupplier(String supplierName, String productName) {
        log.info("Adding product {} to supplier {}", productName, supplierName);
        Supplier supplier = supplierRepo.findBySupplierName(supplierName);

        Product product = productRepo.findByName(productName);
        supplier.getProducts().add(product);
    }
}
