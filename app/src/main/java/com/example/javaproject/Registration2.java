package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration2 extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private FirebaseAuth mAuth;
    private EditText eTStoreName, eTPhoneNumber, eTEmail, eTPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v2);
        mAuth = FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.registerbutton);
        register.setOnClickListener(this);

        eTStoreName = (EditText) findViewById(R.id.editTextStoreName);
        eTPhoneNumber = (EditText) findViewById(R.id.editTextTextPhoneNumber2);
        eTEmail = (EditText) findViewById(R.id.emailAddress);
        eTPassword = (EditText) findViewById(R.id.editTextPasswordReg);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerbutton){
            registerStore(eTStoreName, eTPhoneNumber, eTEmail, eTPassword);
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    private void registerStore(EditText eTStoreName, EditText eTPhoneNumber, EditText eTEmail, EditText eTPassword) {
        String storeName = eTStoreName.getText().toString().trim();
        String phoneNumber = eTPhoneNumber.getText().toString().trim();
        String email = eTEmail.getText().toString();
        String password = eTPassword.getText().toString();
        if (storeName.isEmpty()){
            eTStoreName.setError("Field is empty");
            eTStoreName.requestFocus();
            return;
        }
        if (email.isEmpty()){
            eTEmail.setError("Field is empty");
            eTEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            eTPassword.setError("Field is empty");
            eTPassword.requestFocus();
            return;
        }else if (password.length() < 6){
            eTPassword.setError("Password is too short");
            eTPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password);
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        HashMap<String, Object> user = new HashMap<String, Object>();
        user.put("Store Name", storeName);
        user.put("Phone Number", phoneNumber);
        user.put("Email Address", email);
        DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("StoresTest");
        user_ref.child(userID).updateChildren(user);
    }
}
