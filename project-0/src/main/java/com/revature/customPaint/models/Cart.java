package com.revature.customPaint.models;

public class Cart {
    String orderId;
    String productId;
    String customizeID;
    String userId;
    String orderDate;
    double totalCost;

    public Cart() {
        super();
    }

    public Cart(String orderId, String productId, String customizeID, String userId, String orderDate, double totalCost) {
        this.orderId = orderId;
        this.productId = productId;
        this.customizeID = customizeID;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalCost = totalCost;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomizeID() {
        return customizeID;
    }

    public void setCustomizeID(String customizeID) {
        this.customizeID = customizeID;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
