package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signup, loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = (Button) findViewById(R.id.singupbtn);
        signup.setOnClickListener(this);
        loginbutton = (Button) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(this);
    }

    //arielle changed switch case to if statements
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.singupbtn){
            startActivity(new Intent(this, signupOptions.class));
        }
        if (v.getId() == R.id.loginbutton){
            startActivity(new Intent(this, StoreOwnerActivity.class));
        }
    }
}