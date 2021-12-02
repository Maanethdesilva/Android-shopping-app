package com.example.javaproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */
    public InventoryAdapter(@NonNull Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //get the products information
        String name = getItem(position).getInventory_name();
        int count = getItem(position).getCount();
        double price = getItem(position).getPrice();
        String brand = getItem(position).getBrand();

        //Create object with information
        Product product = new Product(name, count, price, brand);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.inv_name);
        TextView tvCount = (TextView) convertView.findViewById(R.id.inv_count);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.inv_price);
        TextView tvBrand = (TextView) convertView.findViewById(R.id.inv_brand);

        tvName.setText(name);
        tvCount.setText("Stock available: "+count);
        tvPrice.setText("$"+price);
        tvBrand.setText(brand);

        return convertView;

    }
}
