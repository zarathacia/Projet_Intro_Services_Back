package com.example.services.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "cart_item")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne(targetEntity = Product.class,cascade=CascadeType.ALL)
    private Product product;
    private int quantity;
    private double TotalPrice;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;


    }
}