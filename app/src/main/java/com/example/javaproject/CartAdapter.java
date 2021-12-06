package com.example.javaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Product> {
    private final Context mContext;
    int mResource;

    public CartAdapter(@NonNull Context context, int resource, ArrayList<Product> objects) {
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
        int stock = getItem(position).getStock();

        //Create object with information

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = convertView.findViewById(R.id.inv_name);
        TextView tvCount = convertView.findViewById(R.id.inv_count);
        TextView tvPrice = convertView.findViewById(R.id.inv_price);
        TextView tvBrand = convertView.findViewById(R.id.inv_brand);
        TextView tvStock = convertView.findViewById(R.id.inv_stock_available);
        tvStock.setVisibility(View.VISIBLE);
        (convertView.findViewById(R.id.edit_inv_btn)).setVisibility(View.GONE);
        (convertView.findViewById(R.id.imageView)).setVisibility(View.GONE);

        NumberPicker tvQuantity = convertView.findViewById(R.id.editTextNumber);
        tvQuantity.setVisibility(View.VISIBLE);
        tvQuantity.setMaxValue(stock);
        tvQuantity.setMinValue(0);
        tvQuantity.setValue(getItem(position).getCount());

        tvQuantity.setOnScrollListener((view, scrollState) -> {
            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                getItem(position).setCount(tvQuantity.getValue());
                notifyDataSetChanged();
            }
        });

        String priceDisplay = ("$" + price);
        String quantityDisplay = ("Quantity Ordered: " + count);
        String stockDisplay = ("Stock Left: " + (stock - count));
        tvName.setText(name);
        tvPrice.setText(priceDisplay);
        tvBrand.setText(brand);
        tvCount.setText(quantityDisplay);
        tvStock.setText(stockDisplay);

        return convertView;
    }

}

