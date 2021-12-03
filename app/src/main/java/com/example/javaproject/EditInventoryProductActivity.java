package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditInventoryProductActivity extends AppCompatActivity {
    private String storename;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        storename = getIntent().getStringExtra("Storename");

        title = getIntent().getStringExtra("Name");

        TextView tvTitle = (TextView)findViewById(R.id.add_product_title) ;
        tvTitle.setText("Edit Product");

        Button btn = (Button)findViewById(R.id.add_product_submit_btn);
        btn.setText("UPDATE " + title);

        ((EditText)findViewById(R.id.add_product_name)).setText(getIntent().getStringExtra("Name"));
        ((EditText)findViewById(R.id.add_product_brand)).setText(getIntent().getStringExtra("Brand"));
        ((EditText)findViewById(R.id.add_product_count)).setText(""+getIntent().getIntExtra("Count",0));
        ((EditText)findViewById(R.id.add_product_price)).setText(""+getIntent().getDoubleExtra("Price",0));

        btn.setOnClickListener(v->{
            String name = ((EditText)findViewById(R.id.add_product_name)).getText().toString();
            String brand = ((EditText)findViewById(R.id.add_product_brand)).getText().toString();
            int count = Integer.parseInt(((EditText)findViewById(R.id.add_product_count)).getText().toString());
            double price = Double.parseDouble(((EditText)findViewById(R.id.add_product_price)).getText().toString());


            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Name", name);
            map.put("Brand", brand);
            map.put("Count", count);
            map.put("Price", price);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores").child(storename)
                    .child("Inventory");
            ref.child(getIntent().getStringExtra("Name")).updateChildren(map);

            startActivity(new Intent(EditInventoryProductActivity.this, StoreOwnerActivity.class));
        });
    }

}