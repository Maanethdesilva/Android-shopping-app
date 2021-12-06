package com.example.javaproject;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Edit_Quantity extends AppCompatActivity {
    private String storename;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        storename = getIntent().getStringExtra("Storename");

        title = getIntent().getStringExtra("Name");

        TextView tvTitle = findViewById(R.id.add_product_title);
        tvTitle.setText("Edit Product");

        Button btn = findViewById(R.id.add_product_submit_btn);
        btn.setText("UPDATE " + title);

        findViewById(R.id.add_product_name).setVisibility(View.GONE);
        findViewById(R.id.add_product_brand).setVisibility(View.GONE);
        ((EditText)findViewById(R.id.add_product_count)).setText(""+getIntent().getIntExtra("Count",0));
        findViewById(R.id.add_product_price).setVisibility(View.GONE);

        btn.setOnClickListener(v->{
            int count = Integer.parseInt(((EditText)findViewById(R.id.add_product_count)).getText().toString());

            getIntent().getIntArrayExtra("Item")[0] = count;
            finish();


        });
    }

}