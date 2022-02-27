package com.example.services.model;

import javax.persistence.*;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String description;
    private double price;

    @ManyToMany
    private Collection<Category> categories = new ArrayList<>();
    private Long stock;
    private  String imageUrl;
    @ManyToMany
    private Collection<Supplier> suppliers = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String description,double price, Long stock,String imageUrl ) {
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.imageUrl = imageUrl;
    }

}
