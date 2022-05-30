package com.revature.customPaint.models;

public class Store {
    private String id;
    private String street;
    private String name;
    private String city;

    public Store() {
        super();
    }

    public Store(String id, String street, String name, String city) {
        this.id = id;
        this.street = street;
        this.name = name;
        this.city = city;
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

    @Override
    public String toString() {
        return "ID: " + id + "\nName: "  + name + "\nStreet: " + street + "\nCity: " + city;
    }
}