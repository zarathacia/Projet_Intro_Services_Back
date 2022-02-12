package com.example.services.service;

import com.example.services.model.Supplier;

import java.util.List;


public interface SupplierService {
    Supplier saveSupplier(Supplier supplier);




    List<Supplier> getSuppliers();

    Supplier getSupplier(Long id);

    Supplier updateSupplier(Supplier server);


    Boolean deleteProduct(Long id);

    Boolean deleteSupplier(Long id);

    void addProductToSupplier(String supplierName, String productName);
}
