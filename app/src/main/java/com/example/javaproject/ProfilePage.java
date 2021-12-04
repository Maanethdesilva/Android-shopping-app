package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
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

import org.w3c.dom.Text;

import java.util.HashMap;

public class ProfilePage extends AppCompatActivity {
    private static final String TAG = "ProfilePage";

    private Button btnSignOut;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String mUid;
    public static final int BLACK = -16777216;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        mUid = user.getUid();


        final TextView greetingTextView = (TextView) findViewById(R.id.tvGreet);
        final TextView firstNameTextView = (TextView) findViewById(R.id.tvFn);
        final TextView lastNameTextView = (TextView) findViewById(R.id.tvLn);
        final TextView emailTextView = (TextView) findViewById(R.id.tvEmailAddress);
        final TextView phoneNumTextView = (TextView)findViewById(R.id.tvPhoneNum);
        final TextView profileHeaderTextView = (TextView)findViewById(R.id.tvProfileHeader);

        reference.child(mUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String fn = "First Name";
                final String ln = "Last Name";
                final String em = "Email Address";
                final String storeOwner = "isStoreOwner";
                final String sn = "Store Name";
                final String ph = "Phone Number";

                final String space = ": ";
                final String greetingend = ".";
                final String greetingmsg = "Welcome, ";
                final String greetingmsg2 = "You are signed in as a Store Owner of ";


                if (snapshot.child(storeOwner).getValue(boolean.class)){
                    String mSn = snapshot.child(sn).getValue().toString();
                    greetingTextView.setText(greetingmsg2 + mSn + greetingend);
                    firstNameTextView.setText(sn + space + mSn);
                    lastNameTextView.setVisibility(View.GONE);



                } else {
                    String mFn = snapshot.child(fn).getValue().toString();
                    String mLn = snapshot.child(ln).getValue().toString();
                    profileHeaderTextView.setBackgroundColor(BLACK);
                    greetingTextView.setText(greetingmsg + mFn + greetingend);
                    firstNameTextView.setText(fn + space + mFn);
                    lastNameTextView.setText(ln + space + mLn);

                }


                // display email and phone number

                String mEm = snapshot.child(em).getValue().toString();
                String mPh = snapshot.child(ph).getValue().toString();



                phoneNumTextView.setText(ph + space + mPh);
                emailTextView.setText(em + space + mEm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });

        btnSignOut = (Button) findViewById(R.id.custSignOut);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfilePage.this, MainActivity.class));
                Toast.makeText(ProfilePage.this,"Signed Out.", Toast.LENGTH_LONG).show();
            }
        });


    }
    public void viewCustomerHome(View v){
        startActivity(new Intent(ProfilePage.this, CustomerActivity.class));
    }

}