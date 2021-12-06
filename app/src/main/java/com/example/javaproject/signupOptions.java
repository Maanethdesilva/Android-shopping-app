package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class signupOptions extends AppCompatActivity implements View.OnClickListener {

    private TextView signupCustomer;
    private TextView signupStoreOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        signupCustomer = findViewById(R.id.customerbtn);
        signupCustomer.setOnClickListener(this);

        signupStoreOwner = findViewById(R.id.storeownerbtn);
        signupStoreOwner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.storeownerbtn:
                startActivity(new Intent(this, Registration2.class));
                break;
            case R.id.customerbtn:
                startActivity(new Intent(this, Registration.class));
                break;
        }
    }
}