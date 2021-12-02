package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilePage extends AppCompatActivity {
    private static final String TAG = "ProfilePage";
    private Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        btnSignOut = (Button) findViewById(R.id.custSignOut);






    }
    public void viewCustomerHome(View v){
        startActivity(new Intent(ProfilePage.this, CustomerActivity.class));
    }



}