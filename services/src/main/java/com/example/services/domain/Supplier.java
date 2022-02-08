package com.example.services.domain;

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
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @ManyToMany
    private Collection<Product> products = new ArrayList<>();


}
