package com.example.services.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String imageUrl;
    @OneToOne
    private User user;

}
