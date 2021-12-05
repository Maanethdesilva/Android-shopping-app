package com.example.javaproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewDetailsAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    int mResource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */
    public ViewDetailsAdapter(@NonNull Context context, int resource, ArrayList<Product> objects) {
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
        Button tvButton = (Button) convertView.findViewById(R.id.edit_inv_btn);
        ImageView tvDelete = (ImageView) convertView.findViewById(R.id.imageView);

        tvName.setText(name);
        tvCount.setText("Quantity: "+count);
        tvPrice.setText("$"+price);
        tvBrand.setText(brand);

        tvButton.setVisibility(convertView.GONE);
        tvDelete.setVisibility(convertView.GONE);

        return convertView;

    }
}
