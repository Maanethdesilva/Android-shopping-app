package com.example.javaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyOrdersPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orders_page);

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ListView ordersList = (ListView) findViewById(R.id.orders_list);
        ArrayList<Order> ordersArr = new ArrayList<>();

        //check user type
        boolean[] isStoreOwner = new boolean[1];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isStoreOwner[0] = snapshot.child(userID).child("isStoreOwner").getValue(boolean.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //set adapter for customers to OrdersAdapter
        if(!isStoreOwner[0]) {
            OrdersAdapter ordersAdapter = new OrdersAdapter(this, R.layout.orders_list_item, ordersArr);
            ordersList.setAdapter(ordersAdapter);

            ref.child(userID).child("Orders").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ordersArr.clear();
                    for (DataSnapshot order : snapshot.getChildren()) {
                        String storeName = order.child("Storename").getValue().toString();
                        int orderID = order.child("Order ID").getValue(int.class);
                        String status = order.child("Status").getValue().toString();
                        //this only needs to be filled when the user presses view details
                        ArrayList<Product> cart = new ArrayList<>();
                        double total = order.child("Total").getValue(double.class);

                        Order newOrder = new Order(storeName, userID, orderID, status, cart, total);
                        ordersArr.add(newOrder);
                        ordersList.setAdapter(ordersAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        //set adapter to storeOrdersAdapter for store owners
        } else{
            StoreOrdersAdapter storeOrdersAdapter = new StoreOrdersAdapter(this, R.layout.orders_list_item, ordersArr);
            ordersList.setAdapter(storeOrdersAdapter);

            ref.child(userID).child("Orders").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ordersArr.clear();
                    for (DataSnapshot order : snapshot.getChildren()) {
                        String storeName = order.child("Storename").getValue().toString();
                        int orderID = order.child("Order ID").getValue(int.class);
                        String status = order.child("Status").getValue().toString();
                        String customerID = order.child("Customer ID").getValue().toString();
                        //this only needs to be filled when the user presses view details
                        ArrayList<Product> cart = new ArrayList<>();
                        double total = order.child("Total").getValue(double.class);

                        Order newOrder = new Order(storeName, customerID, orderID, status, cart, total);
                        ordersArr.add(newOrder);
                        ordersList.setAdapter(storeOrdersAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        Log.d("LIST IS ", ordersArr.toString());
    }
}