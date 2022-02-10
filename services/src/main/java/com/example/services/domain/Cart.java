package com.example.services.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @OneToOne
    private Account account;
}