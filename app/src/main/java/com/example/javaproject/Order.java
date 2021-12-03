package com.example.javaproject;

import java.util.ArrayList;

public class Order {

    String storeName;
    int orderID;
    String status;
    ArrayList<Product> cart;
    double total;

    public Order(String storeName, int orderID, String status, ArrayList<Product> cart, double total) {
        this.storeName = storeName;
        this.orderID = orderID;
        this.status = status;
        this.cart = cart;
        this.total = total;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getStatus() {return status;}

    public ArrayList<Product> getCart() {return cart;}

    public double getTotal() {return total;}

}
