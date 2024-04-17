package com.marketplace.catalog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "t_product")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "product")
    @JsonIgnore
    private List<Image> images;
    private Long previewImageId;

}
