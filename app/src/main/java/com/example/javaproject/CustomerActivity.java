package com.example.javaproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Button profileButton = findViewById(R.id.btnProfile);
        Button orderButton = findViewById(R.id.btnCart);
        Button notifyButton = findViewById(R.id.btnNotifications);

        profileButton.setOnClickListener(this::viewProfile);
        orderButton.setOnClickListener(this::viewOrder);
        notifyButton.setOnClickListener(this::viewNotification);


        ListView storeListView = findViewById(R.id.custListView);
        ArrayList<Store> allStores = new ArrayList<>();

        CustomerListAdapter adapter = new CustomerListAdapter(this, R.layout.adapter_view_customer, allStores);
        storeListView.setAdapter(adapter);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("Stores");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                allStores.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Object obj = snapshot.child("Storename").getValue();
                    if (obj != null) {
                        Store store = new Store(obj.toString());
                        allStores.add(store);
                    }


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.d("LIST IS ", allStores.toString());


    }

    public void viewProfile(View v) {
        startActivity(new Intent(CustomerActivity.this, ProfilePage.class));
    }

    public void viewNotification(View v) {
        startActivity(new Intent(CustomerActivity.this, NotificationPage.class));
    }

    public void viewOrder(View v) {
        startActivity(new Intent(CustomerActivity.this, MyOrdersPage.class));
    }

}