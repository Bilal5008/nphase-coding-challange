package com.nphase.entity;

import java.math.BigDecimal;

public class Product {
    public  String name;
    public  BigDecimal pricePerUnit;
    public  int quantity;
    public  String category;

    public Product(String name, BigDecimal pricePerUnit, int quantity, String category) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }
    public String getCategory() {
        return category;
    }


}
