package com.example.services.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
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
    @OneToOne(fetch = EAGER)
    private Account account;
}
