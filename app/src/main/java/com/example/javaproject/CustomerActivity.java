package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
    }
    public void viewProfile(View v){
        startActivity(new Intent(CustomerActivity.this, ProfilePage.class));
    }
    public void viewNotification(View v){
        startActivity(new Intent(CustomerActivity.this, NotificationPage.class));
    }
    public void viewOrder(View v){
        startActivity(new Intent(CustomerActivity.this, OrderPage.class));
    }
}