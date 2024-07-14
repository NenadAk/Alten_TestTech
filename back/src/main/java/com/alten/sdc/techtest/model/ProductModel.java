package com.alten.sdc.techtest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTS")
public class ProductModel {
    @Id
    public Integer id;

    @Column
    public String code;

    @Column
    public String name;

    @Column
    public String description;

    @Column
    public String image;

    @Column
    public Integer price;

    @Column
    public String category;

    @Column
    public Integer quantity;

    @Column
    public String inventoryStatus;

    @Column
    public Integer rating;

    public ProductModel(Integer id, String code, String name, String description, String image, Integer price,
            String category, Integer quantity, String inventoryStatus, Integer rating) {
        super();
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.inventoryStatus = inventoryStatus;
        this.rating = rating;
    }

    public ProductModel() {
        super();
    }

    
}
