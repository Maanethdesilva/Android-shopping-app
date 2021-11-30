//package com.example.loginv3;
//
//import java.util.ArrayList;
//
//public class Order {
//    StoreOwner store;
//    ArrayList<Product> cart;
//    ArrayList<Boolean> ready;
//    int orderID;
//    double total;
//    Customer customer;
//    Boolean confirmed;
//
//    public Order(StoreOwner store, Customer customer, int orderID) {
//        this.store = store;
//        this.customer = customer;
//        cart = new ArrayList<Product>();
//        ready = new ArrayList<Boolean>();
//        confirmed = false;
//        total = 0;
//        this.orderID = orderID;
//
//    }
//
//    public void displayOrder() {
//        for(Product product: cart) {
//            //output to UI element
//            //should show product name, brand, quantity ordered and status
//        }
//    }
//    public void confirmOrder() {
//        //should be done in customer instead
//    }
//    public void notifyOwner() {//uploads a message to store owner's notifications
//        store.notifications.add("You have a new order");
//    }
//    public void notifyCustomer() { //uploads a message to customer's notifications
//        customer.notifications.add("Your order is ready");
//    }
//    public void changeReadyStatus(int productIndex) { //changes status of item at given index to ready
//        ready.set(productIndex, true);
//    }
//    public Boolean checkStatus() { //returns true if all items are ready for pickup
//        for(Boolean itemStatus: ready) {
//            if(itemStatus.equals(false)){
//                return false;
//            }
//        }
//        return true;
//    }
//
//}