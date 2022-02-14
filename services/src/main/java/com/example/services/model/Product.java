package com.example.services.model;




import javax.persistence.*;

import lombok.Data;


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

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Image> images = new ArrayList<>();
    @ManyToMany
    private Collection<Supplier> suppliers = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String description, Collection<Category> categories, Long stock, double price, Collection<Image> images) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;

        this.images = images;

        this.price = price;
    }
}
