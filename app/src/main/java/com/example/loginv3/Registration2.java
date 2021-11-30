package com.example.loginv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Registration2 extends AppCompatActivity implements View.OnClickListener{
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v2);
        register = (Button) findViewById(R.id.registerbutton);
        register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerbutton){
            startActivity(new Intent(this, User.class));
        }
    }
}