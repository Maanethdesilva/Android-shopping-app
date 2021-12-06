package com.example.javaproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Edit_Quantity extends AppCompatActivity {
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        title = getIntent().getStringExtra("Name");

        TextView tvTitle = findViewById(R.id.add_product_title);
        String editProduct = ("Edit Product");
        tvTitle.setText(editProduct);

        Button btn = findViewById(R.id.add_product_submit_btn);
        String update = ("UPDATE " + title);
        btn.setText(update);

        (findViewById(R.id.add_product_name)).setVisibility(View.GONE);
        (findViewById(R.id.add_product_brand)).setVisibility(View.GONE);
        String countDisplay = ("" + getIntent().getIntExtra("Count", 0));
        ((EditText) findViewById(R.id.add_product_count)).setText(countDisplay);
        (findViewById(R.id.add_product_price)).setVisibility(View.GONE);

        btn.setOnClickListener(v -> {
            int count = Integer.parseInt(((EditText) findViewById(R.id.add_product_count)).getText().toString());
            getIntent().getIntArrayExtra("Item")[0] = count;
            finish();
        });
    }

}