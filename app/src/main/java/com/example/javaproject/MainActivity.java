package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth FAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button signup = (Button) findViewById(R.id.singupbtn);
        Button login = (Button) findViewById(R.id.loginbutton);
        String email = ((EditText) findViewById(R.id.SignInEmailAddress)).getText().toString();
        String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
        FAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, signupOptions.class));
            finish();
        });

        // This is the validation and authentication code for logging in using email
        login.setOnClickListener(v -> {
            if(TextUtils.isEmpty(email)){
                Toast.makeText(MainActivity.this, "EMPTY EMAIL", Toast.LENGTH_SHORT).show();
            } else if(TextUtils.isEmpty(password)){
                Toast.makeText(MainActivity.this, "EMPTY PASSWORD", Toast.LENGTH_SHORT).show();
            } else {
                FAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                        // This needs to be re-written to send to the correct page
                        startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(MainActivity.this, (e.getLocalizedMessage()), Toast.LENGTH_SHORT).show());
            }
        });
    }
}