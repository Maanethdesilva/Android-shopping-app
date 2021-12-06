package com.example.javaproject;

import androidx.annotation.NonNull;

public class Product {
    private String inventory_name;
    private int count;
    private double price;
    private String brand;
    private int stock;  //To be used for placing orders

    public Product() {

    }

    public Product(String inventory_name, int count, double price, String brand) {
        this.inventory_name = inventory_name;
        this.count = count;
        this.price = price;
        this.brand = brand;
    }

    public Product(String inventory_name, int count, double price, String brand, int stock) {
        this.inventory_name = inventory_name;
        this.count = count;
        this.price = price;
        this.brand = brand;
        this.stock = stock;
    }

    public String getInventory_name() {
        return inventory_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStock() {
        return stock;
    }

    @NonNull
    @Override
    public String toString() {
        return "Product{" +
                "inventory_name='" + inventory_name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
