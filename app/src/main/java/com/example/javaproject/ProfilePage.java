package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {
    public static final int BLACK = -16777216;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        String mUid = user.getUid();

        final TextView greetingTextView = findViewById(R.id.tvGreet);
        final TextView firstNameTextView = findViewById(R.id.tvFn);
        final TextView lastNameTextView = findViewById(R.id.tvLn);
        final TextView emailTextView = findViewById(R.id.tvEmailAddress);
        final TextView phoneNumTextView = findViewById(R.id.tvPhoneNum);
        final TextView profileHeaderTextView = findViewById(R.id.tvProfileHeader);

        reference.child(mUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String fn = "First Name";
                final String ln = "Last Name";
                final String em = "Email Address";
                final String storeOwner = "isStoreOwner";
                final String sn = "Store Name";
                final String ph = "Phone Number";
                final String greeting;
                final String text1;
                final String text2;
                final String text3;
                final String text4;

                Boolean isStore = snapshot.child(storeOwner).getValue(boolean.class);
                if (isStore != null) {
                    if (isStore) {
                        Object object = snapshot.child(sn).getValue();
                        if (object != null) {
                            String mSn = object.toString();
                            greeting = "You are signed in as a store owner of " + mSn + ".";
                            greetingTextView.setText(greeting);

                            text1 = sn + ": " + mSn;
                            firstNameTextView.setText(text1);

                            lastNameTextView.setVisibility(View.GONE);
                        }

                    } else {
                        Object firstObject = snapshot.child(fn).getValue();
                        Object lastObject = snapshot.child(ln).getValue();
                        if (firstObject != null && lastObject != null) {
                            String mFn = firstObject.toString();
                            String mLn = lastObject.toString();
                            profileHeaderTextView.setBackgroundColor(BLACK);
                            greeting = "Welcome, " + mFn + ".";
                            text1 = fn + ": " + mFn;
                            text2 = ln + ": " + mLn;
                            greetingTextView.setText(greeting);
                            firstNameTextView.setText(text1);
                            lastNameTextView.setText(text2);
                        }
                    }

                }

                // display email and phone number
                Object email = snapshot.child(em).getValue();
                Object phone = snapshot.child(ph).getValue();
                if (email != null && phone != null) {
                    String mEm = email.toString();
                    String mPh = phone.toString();
                    text3 = ph + ": " + mPh;
                    text4 = em + ": " + mEm;
                    phoneNumTextView.setText(text3);
                    emailTextView.setText(text4);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });

        Button btnSignOut = findViewById(R.id.custSignOut);

        btnSignOut.setOnClickListener((View v) -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ProfilePage.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(ProfilePage.this, "Signed Out.", Toast.LENGTH_LONG).show();
        });


    }


}