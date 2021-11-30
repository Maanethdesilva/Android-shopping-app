package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NotificationPage extends AppCompatActivity {
    String username = "ryan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        ListView list = findViewById(R.id.notifications_list);



        button.setOnClickListener(v->{
            notifyCustomer(username, "Your order is now ready!");

            //ref.child("New Child").child("Open this").child("Message").setValue("Oh no");
        });

        final ArrayList<String> notifications = new ArrayList<String>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, notifications);
        list.setAdapter(adapter);

        DatabaseReference user = FirebaseDatabase.getInstance().getReference().child("User").child(username).child("Notifications");

        user.addValueEventListener(new ValueEventListener() {
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

    protected void notifyCustomer(String user, String message){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");

        ref.child(user).child("Notifications").push().setValue(message +"\nFrom: " + username).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                DatabaseReference to = ref.child(user).child("Name");
                to.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        Toast.makeText(NotificationPage.this, "Notified " + task.getResult().getValue().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



}