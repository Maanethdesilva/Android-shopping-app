package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class signupOptions extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        TextView signupCustomer = findViewById(R.id.customerbtn);
        signupCustomer.setOnClickListener(this);
        TextView signupStoreOwner = findViewById(R.id.storeownerbtn);
        signupStoreOwner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.storeownerbtn){
            startActivity(new Intent(this, Registration2.class));
        }
        if (v.getId() == R.id.customerbtn){
            startActivity(new Intent(this, Registration.class));
        }
    }
}