package com.example.javaproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreOrdersAdapter extends ArrayAdapter<Order> {

    private final Context mContext;
    int mResource;


    public StoreOrdersAdapter(@NonNull Context context, int resource, ArrayList<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get order information
        String customerID = getItem(position).getCustomerID();
        final String[] customerName = new String[1];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(customerID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                customerName[0] = snapshot.child("First Name").getValue().toString() + snapshot.child("Last Name").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        String orderID = getItem(position).getOrderID();
        String status = getItem(position).getStatus();
        double total = getItem(position).getTotal();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //where we need to store the information
        TextView tvStoreName = convertView.findViewById(R.id.orders_store_name);
        TextView tvCustomerName = convertView.findViewById(R.id.orders_customer_name);
        TextView tvOrderID = convertView.findViewById(R.id.orders_orderID);
        TextView tvStatus = convertView.findViewById(R.id.orders_order_status);
        Button btnViewDetails = convertView.findViewById(R.id.orders_view_details);
        Button btnConfirmOrder = convertView.findViewById(R.id.orders_confirm_order);

        //display the information
        tvCustomerName.setText("Customer Name: "+ customerName[0]);
        tvOrderID.setText("Order ID: " + orderID);
        tvStatus.setText("Order Status: " + status);
        tvStoreName.setVisibility(View.GONE);

        //button to go to details page
        btnViewDetails.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ViewDetailsPage.class);
            intent.putExtra("Customer ID", customerID);
            intent.putExtra("Order ID", orderID);
            intent.putExtra("Total", total);
            mContext.startActivity(intent);
        });

        //button to confirm order
        btnConfirmOrder.setOnClickListener(v -> Toast.makeText(mContext, "Customer has been notified", Toast.LENGTH_LONG));

        return convertView;
    }

}
