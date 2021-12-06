package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText eTFirstName, eTLastName, eTPhoneNumber, eTEmail, eTPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v1);
        mAuth = FirebaseAuth.getInstance();
        Button register = findViewById(R.id.registerbutton);
        register.setOnClickListener(this);

        eTFirstName = findViewById(R.id.editTextName);
        eTLastName = findViewById(R.id.editTextName2);
        eTPhoneNumber = findViewById(R.id.editTextTextPhoneNumber);
        eTEmail = findViewById(R.id.emailAddress);
        eTPassword = findViewById(R.id.editTextPasswordReg);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerbutton){
            register(eTFirstName, eTLastName, eTPhoneNumber, eTEmail, eTPassword);
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

        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {
                    boolean isNotExistingEmail = task.getResult().getSignInMethods().isEmpty();
                    if (isNotExistingEmail) {
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task1 -> {
                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            HashMap<String, Object> user = new HashMap<>();
                            user.put("First Name", firstName);
                            user.put("Last Name", lastName);
                            user.put("Phone Number", phoneNumber);
                            user.put("Email Address", email);
                            user.put("isStoreOwner", false);
                            DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("Users");
                            user_ref.child(userID).updateChildren(user);
                            startActivity(new Intent(this, MainActivity.class));
                        });
                    } else {
                        eTemail.setError("This email is linked to an existing account");
                        eTemail.requestFocus();
                    }
                });
    }
}