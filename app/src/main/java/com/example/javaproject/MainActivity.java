package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signup, loginbutton;
    private EditText eTEmail, eTPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = (Button) findViewById(R.id.singupbtn);
        signup.setOnClickListener(this);
        loginbutton = (Button) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(this);

        eTEmail = (EditText) findViewById(R.id.signInEmailAddress);
        eTPassword = (EditText) findViewById(R.id.signInPassword);
    }

    //arielle changed switch case to if statements
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.singupbtn){
            startActivity(new Intent(this, signupOptions.class));
        }
        if (v.getId() == R.id.loginbutton){

        }
    }
}