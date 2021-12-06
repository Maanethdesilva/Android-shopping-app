package com.example.javaproject;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationPage extends AppCompatActivity {
    String userID = "ryan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_page);

        ListView list = findViewById(R.id.notifications_list);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();


        final ArrayList<String> notifications = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item, notifications);
        list.setAdapter(adapter);



        DatabaseReference user_ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID).child("Notifications");

        user_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notifications.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    notifications.add(0, dataSnapshot.getValue().toString());
                    Log.d("tag", ""+dataSnapshot.getValue().toString());

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}