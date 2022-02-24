package com.example.services.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity

public class CartItem /*extends Product*/ {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private Long quantity;
    @OneToOne
    private Product product;
    @ManyToOne
    private Cart cart;


}
