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
    private Long stock;
    private Double price;
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Image> images = new ArrayList<>();

}
