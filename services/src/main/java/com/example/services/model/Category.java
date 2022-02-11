package com.example.services.model;

import lombok.Data;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String category;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Product> products = new ArrayList<>();

    public Category() {}

    public Category(String category, Collection<Product> products) {
        this.category = category;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", products=" + products +
                '}';
    }
}
