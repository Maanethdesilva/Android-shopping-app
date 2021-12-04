package com.example.javaproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OrdersAdapter extends ArrayAdapter<Order> {

    private Context mContext;
    int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */

    public OrdersAdapter(@NonNull Context context, int resource, ArrayList<Order> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get order information
        String storeName = getItem(position).getStoreName();
        int orderID = getItem(position).getOrderID();
        String status = getItem(position).getStatus();
        ArrayList<Product> cart = getItem(position).getCart();
        double total = getItem(position).getTotal();

        //create order object using that information
        Order order = new Order(storeName, orderID, status, cart, total);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        //where we need to store the information
        TextView tvStoreName = (TextView) convertView.findViewById(R.id.orders_store_name);
        TextView tvOrderID = (TextView) convertView.findViewById(R.id.orders_orderID);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.orders_order_status);
        Button btnViewDetails = (Button) convertView.findViewById(R.id.orders_view_details);

        //display the information
        tvStoreName.setText("Store Name: "+ storeName);
        tvOrderID.setText("Order ID: " + orderID);
        tvStatus.setText("Order Status: " + status);

        //button to go to details page
        btnViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ViewDetailsPage.class);
                intent.putExtra("Customer ID", FirebaseAuth.getInstance().getCurrentUser().getUid());
                intent.putExtra("Order ID", orderID);
                intent.putExtra("Total", total);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

}