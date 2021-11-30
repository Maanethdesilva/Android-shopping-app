package com.example.javaproject;

import java.util.ArrayList;

public class Account {
    private String username;
    private String password;
    private String name;
    private ArrayList<String> notifications;
    private ArrayList<Order> myOrders;

    public Account(String u, String p, String n) {
        username = u;
        password = p;
        name = n;
    }

    public void login() {
        //Navigate to user page.
        //Load all the info from database
    }

    public void changeUsername(String username) {
        this.username = username;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void removeFroMymOrders(Order order) {
        myOrders.remove(order);
    }
    public void addToMyOrders(Order newOrder) {
        myOrders.add(newOrder);
    }
}
