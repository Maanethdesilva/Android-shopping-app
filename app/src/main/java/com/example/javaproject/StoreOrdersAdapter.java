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

    private Context mContext;
    int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */

    public StoreOrdersAdapter(@NonNull Context context, int resource, ArrayList<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get order information
        String storeName = getItem(position).getStoreName();
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
        int orderID = getItem(position).getOrderID();
        String status = getItem(position).getStatus();
        ArrayList<Product> cart = getItem(position).getCart();
        double total = getItem(position).getTotal();

        //create order object using that information
        Order order = new Order(storeName, customerID, orderID, status, cart, total);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //where we need to store the information
        TextView tvStoreName = (TextView) convertView.findViewById(R.id.orders_store_name);
        TextView tvCustomerName = (TextView) convertView.findViewById(R.id.orders_customer_name);
        TextView tvOrderID = (TextView) convertView.findViewById(R.id.orders_orderID);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.orders_order_status);
        Button btnViewDetails = (Button) convertView.findViewById(R.id.orders_view_details);
        Button btnConfirmOrder = (Button) convertView.findViewById(R.id.orders_confirm_order);

        //display the information
        tvCustomerName.setText("Customer Name: "+ customerName[0]);
        tvOrderID.setText("Order ID: " + orderID);
        tvStatus.setText("Order Status: " + status);
        tvStoreName.setVisibility(View.GONE);

        //button to go to details page
        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewDetailsPage.class);
                intent.putExtra("Customer ID", customerID);
                intent.putExtra("Order ID", orderID);
                intent.putExtra("Total", total);
                mContext.startActivity(intent);
            }
        });

        //button to confirm order
        btnConfirmOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Customer has been notified", Toast.LENGTH_LONG);
            }
        });

        return convertView;
    }

}
