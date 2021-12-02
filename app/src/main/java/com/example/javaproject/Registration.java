package com.example.javaproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private Button register;
    private FirebaseAuth mAuth;
    private EditText eTFirstName, eTLastName, eTPhoneNumber, eTEmail, eTPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v1);
        mAuth = FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.registerbutton);
        register.setOnClickListener(this);

        eTFirstName = (EditText) findViewById(R.id.editTextName);
        eTLastName = (EditText) findViewById(R.id.editTextName2);
        eTPhoneNumber = (EditText) findViewById(R.id.editTextTextPhoneNumber);
        eTEmail = (EditText) findViewById(R.id.emailAddress);
        eTPassword = (EditText) findViewById(R.id.editTextPasswordReg);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerbutton){
            register(eTFirstName, eTLastName, eTPhoneNumber, eTEmail, eTPassword);
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    public void register(EditText eTFirstName, EditText eTLastName, EditText eTPhoneNumber, EditText eTemail, EditText eTpassword){
        String firstName = eTFirstName.getText().toString().trim();
        String lastName = eTLastName.getText().toString().trim();
        String phoneNumber = eTPhoneNumber.getText().toString().trim();
        String email = eTemail.getText().toString();
        String password = eTpassword.getText().toString();

        if (firstName.isEmpty()){
            eTFirstName.setError("Field is empty");
            eTFirstName.requestFocus();
            return;
        }
        if (lastName.isEmpty()){
            eTLastName.setError("Field is empty");
            eTLastName.requestFocus();
            return;
        }

        if (email.isEmpty()){
            eTemail.setError("Field is empty");
            eTemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            eTpassword.setError("Field is empty");
            eTpassword.requestFocus();
            return;
        }else if (password.length() < 6){
            eTpassword.setError("Password is too short");
            eTpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password);
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        HashMap<String, Object> user = new HashMap<String, Object>();
        user.put("First Name", firstName);
        user.put("Last Name", lastName);
        user.put("Phone Number", phoneNumber);
        user.put("Email Address", email);
        DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("Users");
        user_ref.child(userID).updateChildren(user);
    }
}