package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Edit_Quantity extends AppCompatActivity {
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

        ((EditText)findViewById(R.id.add_product_name)).setVisibility(View.GONE);
        ((EditText)findViewById(R.id.add_product_brand)).setVisibility(View.GONE);
        ((EditText)findViewById(R.id.add_product_count)).setText(""+getIntent().getIntExtra("Count",0));
        ((EditText)findViewById(R.id.add_product_price)).setVisibility(View.GONE);

        btn.setOnClickListener(v->{
            int count = Integer.parseInt(((EditText)findViewById(R.id.add_product_count)).getText().toString());

            getIntent().getIntArrayExtra("Item")[0] = count;
            finish();


        });
    }

}