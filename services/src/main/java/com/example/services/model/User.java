package com.example.services.model;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String phone;
}
