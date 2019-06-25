package com.learn.reactive.itemclient.domain;


public class Item {

    private String id;
    private String description;
    private Double price;

    public Item(String id, String description, Double price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}