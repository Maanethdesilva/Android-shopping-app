package com.example.javaproject;

public class Product {
    private String name;
    private String brand;
    private double price;
    private StoreOwner store;
    private int quantity;

    public Product(String name, String brand, double price, StoreOwner store, int quantity) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.store = store;
        this.quantity = quantity;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Brand: " + brand);
        System.out.println("Price: $" + price);
        System.out.println("Store: " + store);
        System.out.println("Stock available: " + quantity);
    }

    public void changePrice(double newPrice) {
        price = newPrice;
    }

    public void changeName(String newName) {
        name = newName;
    }

    public void updateQuantity(int addedQuantity) {
        quantity += addedQuantity;
    }
}