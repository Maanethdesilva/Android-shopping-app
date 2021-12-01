package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration2 extends AppCompatActivity {

    private FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v2);

        TextView register = (Button) findViewById(R.id.registerbutton);
        String email = ((EditText) findViewById(R.id.editTextEmailAddressReg)).getText().toString();
        String storename = ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextPasswordReg)).getText().toString();
        String cpassword = ((EditText) findViewById(R.id.editTextTextPassword3)).getText().toString();
        FAuth = FirebaseAuth.getInstance();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(v -> {
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Registration2.this, "EMPTY EMAIL", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(Registration2.this, "EMPTY PASSWORD", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(cpassword)) {
                Toast.makeText(Registration2.this, "EMPTY CONFIRM PASSWORD", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(storename)) {
                Toast.makeText(Registration2.this, "ENTER A NAME FOR YOUR STORE", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(Registration2.this, "PASSWORD HAS TO BE AT LEAST 6 CHARACTERS LONG", Toast.LENGTH_SHORT).show();
            } else if(!password.equals(cpassword)){
                Toast.makeText(Registration2.this, "THE PASSWORDS ENTERED DO NOT MATCH", Toast.LENGTH_SHORT).show();
            } else {
                FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration2.this, task -> {
                    if (task.isSuccessful()) {
                        HashMap<String, Object> user = new HashMap<>();
                        user.put("StoreName", storename);
                        user.put("Email", email);
                        user.put("Storename", storename);
                        userRef.child("User").child(email).updateChildren(user).addOnCompleteListener(Registration2.this, task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(Registration2.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                // Change CustomerActivity to StoreOwner Activity when created
                                startActivity(new Intent(Registration2.this, CustomerActivity.class));
                                finish();
                            }
                        });
                    }
                }).addOnFailureListener(e -> Toast.makeText(Registration2.this, (e.getLocalizedMessage()), Toast.LENGTH_SHORT).show());
            }
        });
    }
}