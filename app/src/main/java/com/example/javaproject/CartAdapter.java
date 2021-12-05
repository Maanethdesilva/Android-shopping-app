package com.example.javaproject;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<Product> {
    private Context mContext;
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
        Product product = new Product(name, count, price, brand);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.inv_name);
        TextView tvCount = (TextView) convertView.findViewById(R.id.inv_count);
        TextView tvPrice = (TextView) convertView.findViewById(R.id.inv_price);
        TextView tvBrand = (TextView) convertView.findViewById(R.id.inv_brand);
        TextView tvStock = (TextView) convertView.findViewById(R.id.inv_stock_available);
        tvStock.setVisibility(View.VISIBLE);
        ((Button) convertView.findViewById(R.id.edit_inv_btn)).setVisibility(View.GONE);
        ((ImageView) convertView.findViewById(R.id.imageView)).setVisibility(View.GONE);


        NumberPicker tvQuantity = (NumberPicker) convertView.findViewById(R.id.editTextNumber);
        tvQuantity.setVisibility(View.VISIBLE);
        tvQuantity.setMaxValue(stock);
        tvQuantity.setMinValue(0);
        tvQuantity.setValue(getItem(position).getCount());

        tvQuantity.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                if(scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE){
                    getItem(position).setCount(tvQuantity.getValue());
                    notifyDataSetChanged();
                }

            }
        });


        tvName.setText(name);
        tvPrice.setText("$"+price);
        tvBrand.setText(brand);
        tvCount.setText("Quantity: "+count);


/*
        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getItem(position).setCount(count+1);
                notifyDataSetChanged();

            }
        });
*/

        return convertView;

    }

}

