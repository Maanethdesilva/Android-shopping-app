package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {

    private FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v1);

        Button register = (Button) findViewById(R.id.registerbutton);
        FAuth = FirebaseAuth.getInstance();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference();

        register.setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.editTextName)).getText().toString().trim();
            String email = ((EditText) findViewById(R.id.editTextEmailAddressReg)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextPasswordReg)).getText().toString();
            String cpassword = ((EditText) findViewById(R.id.editTextTextPassword3)).getText().toString();
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(Registration.this, "EMPTY EMAIL", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(Registration.this, "EMPTY PASSWORD", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(cpassword)) {
                Toast.makeText(Registration.this, "EMPTY CONFIRM PASSWORD", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(name)) {
                Toast.makeText(Registration.this, "ENTER A NAME", Toast.LENGTH_SHORT).show();
            } else if (password.length() < 6) {
                Toast.makeText(Registration.this, "PASSWORD HAS TO BE AT LEAST 6 CHARACTERS LONG", Toast.LENGTH_SHORT).show();
            } else if(!password.equals(cpassword)){
                Toast.makeText(Registration.this, "THE PASSWORDS ENTERED DO NOT MATCH", Toast.LENGTH_SHORT).show();
            } else {
                FAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Registration.this, task -> {
                    if (task.isSuccessful()) {
                        HashMap<String, Object> user = new HashMap<>();
                        user.put("Name", name);
                        user.put("Email", email);
                        userRef.child("User").child(email).updateChildren(user).addOnCompleteListener(Registration.this, task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(Registration.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Registration.this, CustomerActivity.class));
                                finish();
                            }
                        });
                    }
                }).addOnFailureListener(e -> Toast.makeText(Registration.this, (e.getLocalizedMessage()), Toast.LENGTH_SHORT).show());
            }
        });
    }
}