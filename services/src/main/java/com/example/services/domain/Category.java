package com.example.services.domain;

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
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Product> products = new ArrayList<>();
}
