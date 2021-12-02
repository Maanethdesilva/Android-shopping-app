package com.example.javaproject;

public class Store {
    private String storeName;


    public Store(String storeName) {
        this.storeName = storeName;

    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                '}';
    }
}
