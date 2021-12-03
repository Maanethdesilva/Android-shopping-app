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

import java.util.HashMap;

public class ProfilePage extends AppCompatActivity {
    private static final String TAG = "ProfilePage";

    private Button btnSignOut;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String mUid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        mUid = user.getUid();


        final TextView greetingTextView = (TextView) findViewById(R.id.tvgreet);
        final TextView firstNameTextView = (TextView) findViewById(R.id.tvfn);
        final TextView lastNameTextView = (TextView) findViewById(R.id.tvln);
        final TextView emailTextView = (TextView) findViewById(R.id.tvEmailAddress);
        reference.child(mUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String fn = "First Name";
                final String ln = "Last Name";
                final String em = "Email Address";

                String mFn = snapshot.child(fn).getValue().toString();
                String mLn = snapshot.child(ln).getValue().toString();
                String mEm = snapshot.child(em).getValue().toString();




                greetingTextView.setText("Welcome, " + mFn + "!");
                firstNameTextView.setText("First name: "+ mFn);
                lastNameTextView.setText("Last name: " + mLn);
                emailTextView.setText("Email address: " + mEm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });

        /*
        DatabaseReference databaseReference = firebaseDatabase.getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Account account = snapshot.getValue(Account.class);

                firstNameTextView.setText(account.getfName());
                lastNameTextView.setText(account.getlName());
                emailTextView.setText(account.getEmail());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilePage.this, "Something went wrong.", Toast.LENGTH_LONG).show();
            }
        });
*/


        // sign out
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