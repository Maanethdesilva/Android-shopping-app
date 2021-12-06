package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Contract.View {

    EditText eTEmail, eTPassword;
    private Contract.Presenter presenter;

    @Override
    public String getEmail() {
        eTEmail = findViewById(R.id.signInEmailAddress);
        return eTEmail.getText().toString().trim();
    }


    @Override
    public String getPassword() {
        eTPassword = findViewById(R.id.signInPassword);
        return eTPassword.getText().toString();
    }

    @Override
    public void displayEmailMessage(String message) {
        eTEmail.setError(message);
        eTEmail.requestFocus();
    }

    @Override
    public void displayPasswordMessage(String message) {
        eTPassword.setError(message);
        eTPassword.requestFocus();
    }

    @Override
    public void storeOwnerPage() {
        startActivity(new Intent(this, StoreOwnerActivity.class));
    }

    @Override
    public void customerPage() {
        startActivity(new Intent(this, CustomerActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MyPresenter(new MyModel(), this);
        Button signup = findViewById(R.id.singupbtn);
        signup.setOnClickListener(this);
        Button login = findViewById(R.id.loginbutton);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.singupbtn) {
            startActivity(new Intent(this, signupOptions.class));
        }
        if (v.getId() == R.id.loginbutton) {
            presenter.checkCredentials();
        }
    }
}