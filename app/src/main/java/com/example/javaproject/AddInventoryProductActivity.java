package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddInventoryProductActivity extends AppCompatActivity {
    private String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        storename = getIntent().getStringExtra("Storename");
        Button btn = findViewById(R.id.add_product_submit_btn);
        btn.setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.add_product_name)).getText().toString();
            String brand = ((EditText) findViewById(R.id.add_product_brand)).getText().toString();
            int count = Integer.parseInt(((EditText) findViewById(R.id.add_product_count)).getText().toString());
            double price = Double.parseDouble(((EditText) findViewById(R.id.add_product_price)).getText().toString());

            HashMap<String, Object> map = new HashMap<>();
            map.put("Name", name);
            map.put("Brand", brand);
            map.put("Count", count);
            map.put("Price", price);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores").child(storename)
                    .child("Inventory");
            ref.child(name).updateChildren(map);

            startActivity(new Intent(AddInventoryProductActivity.this, StoreOwnerActivity.class));
        });
    }
}