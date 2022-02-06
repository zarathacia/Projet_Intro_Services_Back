package com.example.services.domain;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String path;
    /*@ManyToOne(fetch = FetchType.EAGER)
    private Product product ;*/

    public Image() {
    }

    public Image(String path) {
        this.path = path;
        //this.product = product;
    }
}
