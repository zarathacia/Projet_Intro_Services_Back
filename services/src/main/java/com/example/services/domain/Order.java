package com.example.services.domain;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id ;
    private Date date;
    private Long total;
    @OneToOne
    private Account account;
    @OneToOne(fetch = FetchType.EAGER)
    private Cart cart ;

}
