package com.example.salestax.model;

public class Product {
    private String name;
    private double originalPrice;
    private ProductType type;
    private Boolean isimported;

    public Product(String name, Double price, ProductType type, Boolean isImported) {
        this.name = name;
        this.originalPrice = price;
        this.type = type;
        this.isimported = isImported;

    }

    public String getName() {
        return name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public ProductType getType() {
        return type;
    }

    public Boolean isImported() {
        return isimported;
    }
}
