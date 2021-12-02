package com.example.myproyecto.entities;

import java.io.Serializable;

public class ProductEntity implements Serializable {
    private int id;
    private String productName;
    private int stock;
    private String category;
    private String author;
    private int value;
    private String storeName;
    private String emailStore;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getEmailStore() {
        return emailStore;
    }

    public void setEmailStore(String emailStore) {
        this.emailStore = emailStore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
