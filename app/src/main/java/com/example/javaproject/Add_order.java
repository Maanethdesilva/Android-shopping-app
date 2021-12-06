package com.example.javaproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Add_order extends AppCompatActivity {

    private final double total = 0;
    private String customerID;
    private String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_details_page);
        storename = getIntent().getStringExtra("Storename");
        customerID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ArrayList<Product> cart = new ArrayList<>();

        //set total value
        String totalDisplay = ("TOTAL: $" + total);
        ((TextView) findViewById(R.id.view_details_total)).setText(totalDisplay);

        //set list valuesViewDetailsPage
        ListView products_list = findViewById(R.id.view_details_products_list);

        CartAdapter cartAdapter = new CartAdapter(this, R.layout.inventory_list_item, cart);
        products_list.setAdapter(cartAdapter);

        Button tvUpdateTotal = findViewById(R.id.update_total);
        tvUpdateTotal.setVisibility(View.VISIBLE);

        Button confirmOrder = findViewById(R.id.confirm_order);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores");
        ref.child(storename).child("Inventory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cart.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    Product newP = new Product(item.child("Name").getValue().toString(),
                            0, item.child("Price").getValue(double.class),
                            item.child("Brand").getValue().toString(),
                            item.child("Count").getValue(int.class));
                    cart.add(newP);
                }
                cartAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String updateTotal = ("Update Total");
        tvUpdateTotal.setText(updateTotal);
        tvUpdateTotal.setOnClickListener(v -> {
            double total = 0;
            for (Product item : cart) {
                total += item.getCount() * item.getPrice();
            }
            String dispTotal = ("TOTAL: $" + total);
            ((TextView) findViewById(R.id.view_details_total)).setText(dispTotal);
            if (total == 0) {
                confirmOrder.setVisibility(View.GONE);
            } else {
                confirmOrder.setVisibility(View.VISIBLE);
            }
        });

        confirmOrder.setOnClickListener(v -> {
            double total = 0;
            for (Product item : cart) {
                total += item.getCount() * item.getPrice();
            }

            String totalDisplay2 = ("TOTAL: $" + total);
            ((TextView) findViewById(R.id.view_details_total)).setText(totalDisplay2);

            if (total == 0) {
                confirmOrder.setVisibility(View.GONE);
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_LONG).show();
            } else {
                DatabaseReference store_order = ref.child(storename).child("Orders");
                String key = store_order.push().getKey();

                HashMap<String, Object> map = new HashMap<>();
                map.put("Total", total);
                map.put("Order ID", key);
                map.put("Status", "Not Ready");
                map.put("Storename", storename);
                map.put("Customer ID", customerID);

                DatabaseReference cart_ref = store_order.child(key).child("Cart");

                for (Product item : cart) {
                    if (item.getCount() != 0) {
                        cart_ref.child(item.getInventory_name()).child("Name").setValue(item.getInventory_name());
                        cart_ref.child(item.getInventory_name()).child("Brand").setValue(item.getBrand());
                        cart_ref.child(item.getInventory_name()).child("Count").setValue(item.getCount());
                        cart_ref.child(item.getInventory_name()).child("Price").setValue(item.getPrice());

                    }
                }
                store_order.child(key).updateChildren(map);

                map.clear();
                map.put("Total", total);
                map.put("Order ID", key);
                map.put("Status", "Not Ready");
                map.put("Storename", storename);
                map.put("Customer ID", customerID);

                DatabaseReference cust_ref1 = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(customerID).child("Orders").child(key);
                DatabaseReference cust_ref = cust_ref1.child("Cart");

                for (Product item : cart) {
                    if (item.getCount() != 0) {
                        cust_ref.child(item.getInventory_name()).child("Name").setValue(item.getInventory_name());
                        cust_ref.child(item.getInventory_name()).child("Brand").setValue(item.getBrand());
                        cust_ref.child(item.getInventory_name()).child("Count").setValue(item.getCount());
                        cust_ref.child(item.getInventory_name()).child("Price").setValue(item.getPrice());
                    }
                }
                cust_ref1.updateChildren(map);
            }
        });
    }
}