package com.revature.customPaint.models;

public class Inventory {
    private String storeId;
    private String productId;
    private int quantity;

    public Inventory() {
        super();
    }

    public Inventory(String storeId, String productId, int quantity) {
        this.storeId = storeId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "store ID: " + storeId + "\nProduct ID: " + productId + "\nQuantity: " + quantity;
    }
}
