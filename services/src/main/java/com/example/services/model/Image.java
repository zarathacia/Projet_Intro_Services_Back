package com.example.services.model;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product ;
}
