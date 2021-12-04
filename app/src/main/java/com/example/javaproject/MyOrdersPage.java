package com.example.javaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
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

        String customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ListView ordersList = (ListView) findViewById(R.id.orders_list);
        ArrayList<Order> ordersArr = new ArrayList<>();

        //set orders list to take custom adapter and get firebase reference for orders
        OrdersAdapter ordersAdapter = new OrdersAdapter(this, R.layout.orders_list_item, ordersArr);
        ordersList.setAdapter(ordersAdapter);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(customerID).child("Orders");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ordersArr.clear();
                for(DataSnapshot order: snapshot.getChildren()){
                    String storeName = order.child("Storename").getValue().toString();
                    int orderID = order.child("Order ID").getValue(int.class);
                    String status = order.child("Status").getValue().toString();
                    //this only needs to be filled when the user presses view details
                    ArrayList<Product> cart = new ArrayList<>();
                    double total = order.child("Total").getValue(double.class);

                    Order newOrder = new Order(storeName, orderID, status, cart, total);
                    ordersArr.add(newOrder);
                    ordersList.setAdapter(ordersAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("LIST IS ", ordersArr.toString());

    }
}