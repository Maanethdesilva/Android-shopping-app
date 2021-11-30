package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    //    private EditText name, email, password;
    private TextView register;

    //initialize firebase authentication


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v1);

        register = (Button) findViewById(R.id.registerbutton);
        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerbutton:
                String name = ((EditText) findViewById(R.id.editTextName)).getText().toString().trim();
                String email = ((EditText) findViewById(R.id.editTextEmailAddressReg)).getText().toString();
                String password = ((EditText) findViewById(R.id.editTextPasswordReg)).getText().toString();


                register(name, email, password);


        }
    }

    //Hello there
    //comment
    public void register(String name, String email, String password){
        HashMap<String, Object> user = new HashMap<String, Object>();
        user.put("Name", name);
        user.put("Email", email);
        DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("User");
        user_ref.child(email).updateChildren(user);
        //Create a customer account

    }
}