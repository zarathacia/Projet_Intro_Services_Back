package com.example.services.domain;

import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToMany
    private Collection<Category> categories = new ArrayList<>();
    private Long stock;
    private Double price;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Image> images = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String description, Collection<Category> categories, Long stock, Double price, Collection<Image> images) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.images = images;
    }
}
