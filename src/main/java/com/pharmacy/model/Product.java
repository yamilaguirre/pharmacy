package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = true)
    private String code;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "unit", nullable = true)
    private String unit;

    @Column(name = "weight", nullable = true)
    private Float weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    @ToString.Exclude
    private Category category;

    @Column(name = "purchase_price", nullable = true)
    private Float purchasePrice;

    @Column(name = "sale_price", nullable = true)
    private Float salePrice;

    @Column(name = "image_url", nullable = true)
    private String imageUrl;

    @Column(name = "current_stock", nullable = true)
    private Float currentStock;

    @Column(name = "desired_stock", nullable = true)
    private Integer desiredStock;
}