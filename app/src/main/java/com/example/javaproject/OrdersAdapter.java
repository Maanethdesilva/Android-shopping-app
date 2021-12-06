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

import java.util.ArrayList;

public class OrdersAdapter extends ArrayAdapter<Order> {

    private final Context mContext;
    int mResource;

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
        String customerID = getItem(position).getCustomerID();
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
        String storeNameDisplay = ("Store Name: " + storeName);
        String orderIDDisplay = ("Order ID: " + orderID);
        String orderStatusDisplay = ("Order Status: " + status);
        tvStoreName.setText(storeNameDisplay);
        tvOrderID.setText(orderIDDisplay);
        tvStatus.setText(orderStatusDisplay);
        tvCustomerName.setVisibility(View.GONE);
        btnConfirmOrder.setVisibility(View.GONE);

        //button to go to details page
        btnViewDetails.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ViewDetailsPage.class);
            intent.putExtra("Customer ID", customerID);
            intent.putExtra("Order ID", orderID);
            intent.putExtra("Total", total);
            mContext.startActivity(intent);
        });

        return convertView;
    }

}