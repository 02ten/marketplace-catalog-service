package com.marketplace.catalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creationDate")
    private Date creationDate;
    @Column(name="address")
    private String address;
    private String telephone;
    private Long userId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_products_id")
    private List<OrderProducts> productsList;
    private boolean active;

    private double summary;
    private String status;
    private String payment;
}
