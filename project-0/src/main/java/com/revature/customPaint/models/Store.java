package com.revature.customPaint.models;

public class Store {
    private String id;
    private String street;
    private String name;
    private String city;
    private String state;

    public Store() {
        super();
    }

    public Store(String id, String street, String name, String city, String state) {
        this.id = id;
        this.street = street;
        this.name = name;
        this.city = city;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nCity: " + city + "\nState: " + state;
    }
}