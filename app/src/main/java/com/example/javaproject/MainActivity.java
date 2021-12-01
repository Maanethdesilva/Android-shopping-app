package com.example.javaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signup, loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = (TextView) findViewById(R.id.singupbtn);
        signup.setOnClickListener(this);
        loginbutton = (TextView) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.singupbtn:
                startActivity(new Intent(this, signupOptions.class));
                break;
            case R.id.loginbutton:
                startActivity(new Intent(this, StoreOwnerActivity.class));
                break;
        }
    }
}