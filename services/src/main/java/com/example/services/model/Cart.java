package com.example.services.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @OneToOne
    private User user;
    @ManyToOne
    CartItem cartItem;


}
