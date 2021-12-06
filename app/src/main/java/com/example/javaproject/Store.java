package com.example.javaproject;

import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return "Store{" +
                "storeName='" + storeName + '\'' +
                '}';
    }
}
