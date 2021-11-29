package com.example.loginv3;
import java.util.ArrayList;
import java.util.UUID;

public class Customer {
    public void newOrder() {
        String id = UUID.randomUUID().toString();
        ArrayList<Product> cart;
        ArrayList<int> quantity;
        ArrayList<Boolean> status;
        Order order = new Order(cart, quantity, status, id, 0, false, "");
        myOrders.add(order);


    }
    public void addProduct(Order order, Product product, Quantity quantity) {
        order.cart.add(product);
        order.quantity.add(quantity);
        order.ready.add(false);



    }

    public void viewStores() { // where do we get the list of stores from?

    }

    public void markOrderPickedUp(Order order) {
        order.status.replace("", "Order picked up by customer");
    }
}
