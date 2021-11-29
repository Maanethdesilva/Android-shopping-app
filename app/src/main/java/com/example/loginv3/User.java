package com.example.loginv3;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//this class is used to get user information when signing up to allow
//the program to get name, email, and password to allow firebase authentication

public class User extends AppCompatActivity {
    private EditText eTextName, eTextEmail, eTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eTextName = (EditText) findViewById(R.id.editTextName);
        eTextEmail = (EditText) findViewById(R.id.editTextEmailAddressReg);
        eTextPassword = (EditText) findViewById(R.id.editTextPasswordReg);

        String name = eTextName.getText().toString();
        String email = eTextEmail.getText().toString();
        String password = eTextPassword.getText().toString();

        //validation of this information in progress by Mohammed

        //firebase stuff in progress by Maaneth
        //create new user with firebase
        //check if user registered
        //if user registered create a new instance of customer or store owner
        //then redirect to login page
    }

}
