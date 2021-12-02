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
    private EditText eTName, eTEmail, eTPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v1);
        mAuth = FirebaseAuth.getInstance();
        register = (Button) findViewById(R.id.registerbutton);
        register.setOnClickListener(this);

        eTName = (EditText) findViewById(R.id.editTextName);
        eTEmail = (EditText) findViewById(R.id.emailAddress);
        eTPassword = (EditText) findViewById(R.id.editTextPasswordReg);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerbutton){
            register(eTName, eTEmail, eTPassword);
        }
    }

    public void register(EditText eTname, EditText eTemail, EditText eTpassword){
        String email = eTemail.getText().toString();
        String password = eTpassword.getText().toString();
        String name = eTname.getText().toString();

//        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
//            Toast.makeText(Registration.this, "Please enter all information", Toast.LENGTH_LONG).show();
//            eTname.requestFocus();
//            return;
//        }
//        Pattern pattern = Pattern.compile(".com");
//        Matcher matcher = pattern.matcher(email);
//        if (!matcher.matches()){
//            eTemail.setError("Email Address invalid");
//            eTemail.requestFocus();
//            return;
//        }
        if (name.isEmpty()){
            eTname.setError("Field is empty");
            eTname.requestFocus();
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

        //the following line of code was taken from youtube vid
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()){
//                    Account user = new Account (name, email);
//                    String user_ref = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                    FirebaseDatabase.getInstance().getReference("Users").child(user_ref).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                Toast.makeText(Registration.this, "successful registration", Toast.LENGTH_SHORT).show();
//                            }
//                            else{
//                                Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }else{
//                    Toast.makeText(Registration.this, "Registration failed", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        mAuth.createUserWithEmailAndPassword(email, password);
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        HashMap<String, Object> user = new HashMap<String, Object>();
        user.put("Name", name);
        user.put("Email", email);
        DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("Users2");
        user_ref.child(userID).updateChildren(user);
        //Create a customer account
    }
}