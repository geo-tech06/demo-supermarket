package com.Supermarket.demo.dto;

import java.math.BigDecimal;

public class ProductInfo {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String category;
    private String supplier;
    private String expirationStatus;

    public ProductInfo() {}

    public ProductInfo(String name, BigDecimal price, Integer quantity,
                       String category, String supplier, String expirationStatus) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.supplier = supplier;
        this.expirationStatus = expirationStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getExpirationStatus() {
        return expirationStatus;
    }

    public void setExpirationStatus(String expirationStatus) {
        this.expirationStatus = expirationStatus;
    }
}