package com.revature.customPaint.models;

public class Cart {
    String id;
    String productId;
    String productName;
    double productPrice;
    int productQuantity;

    public Cart() {
        super();
    }

    public Cart(String id, String productId, String productName, double productPrice, int productQuantity) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
    @Override
    public String toString() {
        return "ID: " + id + "\nProduct Id: "  + productId + "\nProduct Name: " + productName + "\nProduct Price: " + productPrice + "\nProduct Quantity: " + productQuantity;
    }

    public String partialToString(){
        return "Product Name: " + productName + "\nProduct Price: " + productPrice + "\nProduct Quantity: " + productQuantity;
    }
}
