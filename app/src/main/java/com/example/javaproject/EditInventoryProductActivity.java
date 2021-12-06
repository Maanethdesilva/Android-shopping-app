package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class EditInventoryProductActivity extends AppCompatActivity {
    private String storename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        storename = getIntent().getStringExtra("Storename");
        String title = getIntent().getStringExtra("Name");
        TextView tvTitle = findViewById(R.id.add_product_title) ;
        String editProduct = ("Edit Product");
        tvTitle.setText(editProduct);

        Button btn = findViewById(R.id.add_product_submit_btn);
        String updateDisplay = ("UPDATE " + title);
        btn.setText(updateDisplay);

        String countDisplay = (""+getIntent().getIntExtra("Count",0));
        String priceDisplay = (""+getIntent().getDoubleExtra("Price",0));
        ((EditText)findViewById(R.id.add_product_name)).setText(getIntent().getStringExtra("Name"));
        ((EditText)findViewById(R.id.add_product_brand)).setText(getIntent().getStringExtra("Brand"));
        ((EditText)findViewById(R.id.add_product_count)).setText(countDisplay);
        ((EditText)findViewById(R.id.add_product_price)).setText(priceDisplay);

        btn.setOnClickListener(v->{
            String name = ((EditText)findViewById(R.id.add_product_name)).getText().toString();
            String brand = ((EditText)findViewById(R.id.add_product_brand)).getText().toString();
            int count = Integer.parseInt(((EditText)findViewById(R.id.add_product_count)).getText().toString());
            double price = Double.parseDouble(((EditText)findViewById(R.id.add_product_price)).getText().toString());

            HashMap<String, Object> map = new HashMap<>();
            map.put("Name", name);
            map.put("Brand", brand);
            map.put("Count", count);
            map.put("Price", price);
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Users");

            users.child(userId).child("Store Name").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    storename = snapshot.getValue().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores").child(storename)
                            .child("Inventory");
                    ref.child(getIntent().getStringExtra("Name")).setValue(null);
                    ref.child(name).updateChildren(map);
                    startActivity(new Intent(EditInventoryProductActivity.this, StoreOwnerActivity.class));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });
    }

}