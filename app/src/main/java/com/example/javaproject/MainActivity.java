package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signup, loginbutton;
    private EditText eTEmail, eTPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.singupbtn);
        signup.setOnClickListener(this);
        loginbutton = findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(this);

        eTEmail = findViewById(R.id.signInEmailAddress);
        eTPassword = findViewById(R.id.signInPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.singupbtn){
            startActivity(new Intent(this, signupOptions.class));
        }
        if (v.getId() == R.id.loginbutton){
            login();
        }
    }

    private void login() {
        String email = eTEmail.getText().toString().trim();
        String password = eTPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            if (task.isSuccessful()){
                DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("Users");
                user_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean isStoreOwner = snapshot.child(userID).child("isStoreOwner").getValue(boolean.class);
                        if (isStoreOwner) {
                            startActivity(new Intent(MainActivity.this, StoreOwnerActivity.class));
                        }else {
                            startActivity(new Intent(MainActivity.this, CustomerActivity.class));
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            } else {
                eTEmail.setError("Invalid Credentials");
                eTEmail.requestFocus();
            }
        });
    }
}