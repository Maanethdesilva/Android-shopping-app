package com.example.javaproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewDetailsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details_page);

        String orderID = getIntent().getStringExtra("Order ID");
        double total = getIntent().getDoubleExtra("Total", 0);
        String customerID = getIntent().getStringExtra("Customer ID");
        ArrayList<Product> cart = new ArrayList<>();

        //set total value
        ((TextView)findViewById(R.id.view_details_total)).setText("TOTAL: $" + total);

        //set list valuesViewDetailsPage
        ListView products_list = findViewById(R.id.view_details_products_list);


        ViewDetailsAdapter cartAdapter = new ViewDetailsAdapter(this, R.layout.inventory_list_item, cart);
        products_list.setAdapter(cartAdapter);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(customerID).child("Orders");
        ref.child(orderID).child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cart.clear();
                for(DataSnapshot item: snapshot.getChildren()) {
                    Product newP = new Product(item.child("Name").getValue().toString(),
                            item.child("Count").getValue(int.class), item.child("Price").getValue(double.class),
                            item.child("Brand").getValue().toString());
                    cart.add(newP);
                }
                cartAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}