package com.revature.customPaint.models;

public class History {
    private String id;
    private String userId;
    private String orderDate;
    private double totalPrice;

    public History(String id, String userId, String orderDate, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nUser ID: " + userId + "\nOrder Date: " + orderDate + "\nTotal Price: " +
                totalPrice;
    }
}
