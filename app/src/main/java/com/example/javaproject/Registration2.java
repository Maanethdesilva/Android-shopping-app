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

    private FirebaseAuth mAuth;
    private EditText eTStoreName, eTPhoneNumber, eTEmail, eTPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_v2);
        mAuth = FirebaseAuth.getInstance();
        Button register = findViewById(R.id.registerbutton);
        register.setOnClickListener(this);

        eTStoreName = findViewById(R.id.editTextStoreName);
        eTPhoneNumber = findViewById(R.id.editTextTextPhoneNumber2);
        eTEmail = findViewById(R.id.emailAddress);
        eTPassword = findViewById(R.id.editTextPasswordReg);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerbutton){
            registerStore(eTStoreName, eTPhoneNumber, eTEmail, eTPassword);
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
        mAuth.fetchSignInMethodsForEmail(email)
                .addOnCompleteListener(task -> {
                    boolean isNotExistingEmail = task.getResult().getSignInMethods().isEmpty();
                    if (isNotExistingEmail) {
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task1 -> {
                            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            HashMap<String, Object> user = new HashMap<>();
                            user.put("Store Name", storeName);
                            user.put("Phone Number", phoneNumber);
                            user.put("Email Address", email);
                            user.put("isStoreOwner", true);
                            DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("Users");
                            user_ref.child(userID).updateChildren(user);

                            HashMap<String, Object> store = new HashMap<>();
                            store.put("Storename", storeName);
                            store.put("OwnerId", userID);
                            DatabaseReference store_ref = FirebaseDatabase.getInstance().getReference().child("Stores");
                            store_ref.child(storeName).updateChildren(store);
                            startActivity(new Intent(this, MainActivity.class));
                        });
                    } else {
                        eTEmail.setError("This email is linked to an existing account");
                        eTEmail.requestFocus();
                    }
                });
    }
}
