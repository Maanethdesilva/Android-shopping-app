package com.example.javaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
        //check user type
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isStoreOwner = snapshot.child("isStoreOwner").getValue(boolean.class);
                if(isStoreOwner){
                     output(snapshot.child("Store Name").getValue().toString());
                } else{
                    output();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    protected  void output(){
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ListView ordersList = findViewById(R.id.orders_list);
        ArrayList<Order> ordersArr = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("Orders");
        OrdersAdapter ordersAdapter = new OrdersAdapter(this, R.layout.orders_list_item, ordersArr);
        ordersList.setAdapter(ordersAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ordersArr.clear();
                for (DataSnapshot order : snapshot.getChildren()) {
                    String storeName = order.child("Storename").getValue().toString();
                    String orderID = order.child("Order ID").getValue().toString();
                    String status = order.child("Status").getValue().toString();
                    //this only needs to be filled when the user presses view details
                    double total = order.child("Total").getValue(double.class);

                    Order newOrder = new Order(storeName, userID, orderID, status,  new ArrayList<>(), total);
                    ordersArr.add(newOrder);
                }
                ordersAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ERROR", "orders aren't being read");
            }
        });
    }
    protected void output(String storename){
        //set adapter for customers to OrdersAdapter
        ListView ordersList = findViewById(R.id.orders_list);
        ArrayList<Order> ordersArr = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores").child(storename).child("Orders");

        StoreOrdersAdapter storeOrdersAdapter = new StoreOrdersAdapter(this, R.layout.orders_list_item, ordersArr);
        ordersList.setAdapter(storeOrdersAdapter);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ordersArr.clear();
                for (DataSnapshot order : snapshot.getChildren()) {
                    String orderID = order.child("Order ID").getValue().toString();
                    String status = order.child("Status").getValue().toString();
                    String customerID = order.child("Customer ID").getValue().toString();
                    //this only needs to be filled when the user presses view details
                    ArrayList<Product> cart = new ArrayList<>();
                    double total = order.child("Total").getValue(double.class);
                    Order newOrder = new Order(storename, customerID, orderID, status, cart, total);
                    ordersArr.add(newOrder);
                }
                storeOrdersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }


}