package com.example.javaproject;

public class Product {
    private String inventory_name;
    private int count;
    private double price;

    public Product(){

    }

    public Product(String inventory_name, int count, double price) {
        this.inventory_name = inventory_name;
        this.count = count;
        this.price = price;
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

    public void setInventory_name(String inventory_name) {
        this.inventory_name = inventory_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Product{" +
                "inventory_name='" + inventory_name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
