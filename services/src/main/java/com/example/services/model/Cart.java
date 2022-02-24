package com.example.services.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;

    @CreationTimestamp
    private Date dateCreated;

    @UpdateTimestamp
    private Date lastUpdated;
    @OneToOne
    private User user;
   /* @OneToMany//@ManyToOne
    //CartItem cartItem;
    private List<CartItem> cartItems = new ArrayList<>();
    public void add(CartItem item){
        if(item != null){
            if(cartItems == null){
                cartItems = new ArrayList<>();
            }
            cartItems.add(item);
            //item.setCart(this);
        }
    }*/
    @ManyToOne

    Product cartItem;

}
