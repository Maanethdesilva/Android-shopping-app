package com.example.javaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewDetailsPage extends AppCompatActivity {

    private int orderID;
    private double total;
    private String customerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details_page);

        orderID = getIntent().getIntExtra("Order ID", 0);
        total = getIntent().getDoubleExtra("Total", 0);
        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ArrayList<Product> cart = new ArrayList<>();

        //set total value
        ((TextView)findViewById(R.id.view_details_total)).setText("TOTAL: $" + total);

        //set list values
        ListView products_list = (ListView) findViewById(R.id.view_details_products_list);

        ViewDetailsAdapter cartAdapter = new ViewDetailsAdapter(this, R.layout.inventory_list_item, cart);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(customerID).child("Orders");
        ref.child(String.valueOf(orderID)).child("Cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cart.clear();
                for(DataSnapshot item: snapshot.getChildren()) {
                    Product newP = new Product(item.child("Name").getValue().toString(),
                            item.child("Count").getValue(int.class), item.child("Price").getValue(double.class),
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