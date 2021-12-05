package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Add_order extends AppCompatActivity {

    private double total = 0;
    private String customerID;
    private String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details_page);
        storename = getIntent().getStringExtra("Storename");

        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ArrayList<Product> cart = new ArrayList<Product>();

        //set total value
        ((TextView)findViewById(R.id.view_details_total)).setText("TOTAL: $" + total);

        //set list valuesViewDetailsPage
        ListView products_list = (ListView) findViewById(R.id.view_details_products_list);

        CartAdapter cartAdapter = new CartAdapter(this, R.layout.inventory_list_item, cart);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores");
        ref.child(storename).child("Inventory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cart.clear();
                for(DataSnapshot item: snapshot.getChildren()) {
                    Product newP = new Product(item.child("Name").getValue().toString(),
                            0, item.child("Price").getValue(double.class),
                            item.child("Brand").getValue().toString());
                    cart.add(newP);
                    products_list.setAdapter(cartAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            };
        });
    }
}