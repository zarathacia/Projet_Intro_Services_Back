package com.example.services.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Supplier {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String supplierName;
    private String email;
    private String phoneNumber;
    private String address;
    @ManyToMany
    private Collection<Product> products = new ArrayList<>();
    public Supplier() {}

    public Supplier(String supplierName, Collection<Product> products) {
        this.supplierName = supplierName;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplierName;
    }

    public void setSupplier(String supplierName) {
        this.supplierName = supplierName;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void  setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", supplier='" + supplierName + '\'' +
                ", products=" + products +
                '}';
    }


}
