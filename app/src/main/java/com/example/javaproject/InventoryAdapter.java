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

public class InventoryAdapter extends ArrayAdapter<Product> {
    private final Context mContext;
    int mResource;

    /**
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

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tvName = convertView.findViewById(R.id.inv_name);
        TextView tvCount = convertView.findViewById(R.id.inv_count);
        TextView tvPrice = convertView.findViewById(R.id.inv_price);
        TextView tvBrand = convertView.findViewById(R.id.inv_brand);
        Button tvButton = convertView.findViewById(R.id.edit_inv_btn);
        ImageView tvDelete = convertView.findViewById(R.id.imageView);

        String stockAvailable = ("Stock available: " + count);
        String priceDisplay = ("$" + price);
        tvName.setText(name);
        tvCount.setText(stockAvailable);
        tvPrice.setText(priceDisplay);
        tvBrand.setText(brand);

        tvButton.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, EditInventoryProductActivity.class);
            intent.putExtra("Name", name);
            intent.putExtra("Brand", brand);
            intent.putExtra("Count", count);
            intent.putExtra("Price", price);
            intent.putExtra("Storename", "Mcdonalds");  //Change when we have the user
            mContext.startActivity(intent);
        });

        tvDelete.setOnClickListener(v -> {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference users = FirebaseDatabase.getInstance().getReference().child("Users");
            users.child(userId).child("Store Name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String storename = snapshot.getValue().toString();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Stores").child(storename)
                            .child("Inventory");
                    ref.child(name).setValue(null);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });

        return convertView;

    }
}
