package com.goruslan.eCommerce.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

//    @Column(name = "image_url", length = 10000) // Fix the length
//    private String imageUrl;

//    @Column(name = "units_in_stock")
//    private int unitsInStock;

}
