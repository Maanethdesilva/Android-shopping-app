package com.example.javaproject;

import java.util.ArrayList;

public class Order {

    String storeName;
    String customerID;
    String orderID;
    String status;
    ArrayList<Product> cart;
    double total;

    public Order(String storeName, String customerName, String orderID, String status, ArrayList<Product> cart, double total) {
        this.storeName = storeName;
        this.customerID = customerName;
        this.orderID = orderID;
        this.status = status;
        this.cart = cart;
        this.total = total;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getCustomerID() {return customerID;}

    public String getOrderID() { return orderID;}

    public String getStatus() {return status;}

    public double getTotal() {return total;}

}