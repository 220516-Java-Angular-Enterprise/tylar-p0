package com.revature.customPaint.models;

public class Product {
    private String id;
    private String name;
    private String category;
    private String description;
    private double cost;

    public Product() {
        super();
    }

    public Product(String id, String name, String category, String description, double cost) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + "\nCategory: " + category + "\nDescription: " +
                description + "\nCost: " + cost;
    }
}
