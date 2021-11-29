//package com.example.loginv3;
//import java.util.ArrayList;
//
//public class StoreOwner {
//    String storeName;
//    ArrayList<Product> stock;
//
//    public StoreOwner(String storeName, ArrayList<Product> stock){ // constructor
//        this.storeName = storeName;
//        this.stock = stock;
//    }
//
//    public void listStock(){ // lists the store stock
//        for(Product p: stock) {
//            p.displayInfo();
//
//        }
//    }
//
//    public void markOrderReady(Order order) { // mark a specified order confirmed as True
//        order.confirmed = true;
//    }
//
//    public void viewOrders() {
//        for (Order o: myOrders) {
//            o.displayOrder();
//        }
//    }
//
//    public void addProduct(product Product) {
//        stock.add(product);
//    }
//
//    public void removeProduct(product Product) {
//        stock.remove(product);
//    }
//}
