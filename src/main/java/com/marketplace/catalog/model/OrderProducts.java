package com.marketplace.catalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_order_products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private Long userId;

    @Column(name="quantity")
    private Long quantity;
}
