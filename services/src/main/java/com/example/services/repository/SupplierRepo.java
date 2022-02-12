package com.example.services.repository;

import com.example.services.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    Supplier findBySupplierName(String supplierName);

}
