package com.shop.store.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.shop.store.R;
import com.shop.store.model.Store;

import java.util.ArrayList;

public class StoreAdapter extends ArrayAdapter<Store> {
    Activity context;
    ArrayList<Store> stores;
    int layoutResource;

    public StoreAdapter(Activity context, int resource, ArrayList<Store> objects)
    {
        super(context, resource, objects);
        this.context = context;
        this.stores = objects;
        this.layoutResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=this.context.getLayoutInflater();//build layout thanh java de android su dung
        View row=layoutInflater.inflate(this.layoutResource,null);
        TextView storeId = row.findViewById(R.id.txt_row_show_idStore);
        storeId.setText(stores.get(position).getId().toString());
        TextView storeName = row.findViewById(R.id.txt_row_show_nameStore);
        storeName.setText(stores.get(position).getName());

        ImageView image = row.findViewById(R.id.imgeViewAdStore);
        Glide.with(context)
                .load(stores.get(position).getImage())
                .into(image);
        return row;
    }
}
