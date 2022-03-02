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
    private String suppliertype;
    private String location;
    private String delivery;
    private String bio;
    private String imageUrl;
    @ManyToMany
    private Collection<Product> products = new ArrayList<>();
    public Supplier() {}

    public Supplier(String supplierName, Collection<Product> products, String email,
    String phoneNumber, String address, String suppliertype, String location,String delivery, String bio, String imageUrl) {
        this.supplierName = supplierName;
        this.products = products;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.suppliertype=suppliertype;
        this.location=location;
        this.delivery=delivery;
        this.bio=bio;
        this.imageUrl=imageUrl;
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
