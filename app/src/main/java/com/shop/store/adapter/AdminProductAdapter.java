package com.shop.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shop.store.databinding.AdminProductListviewBinding;
import com.shop.store.model.Product;

import java.util.ArrayList;

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.Viewholder> {
    private Context context;
    private ArrayList<Product> products;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(AdminProductListviewBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.adminProductListviewBinding.txtRowShowIdStore.setText(products.get(position).getId().toString());
        holder.adminProductListviewBinding.txtRowShowNameStore.setText(products.get(position).getName());
        holder.adminProductListviewBinding.txtRowPriceProduct.setText(products.get(position).getPrice().toString());
        holder.adminProductListviewBinding.txtRowShowNumber.setText("The remaining amount: " + products.get(position).getStatus());
        Glide.with(context)
                .load(products.get(position).getImage())
                .into(holder.adminProductListviewBinding.imgeViewAdProduct);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private AdminProductListviewBinding adminProductListviewBinding;

        public Viewholder(@NonNull AdminProductListviewBinding itemView) {
            super(itemView.getRoot());
            adminProductListviewBinding = itemView;
        }
    }

    public AdminProductAdapter(Context context, ArrayList<Product> objects) {
        this.context = context;
        this.products = objects;
    }


}
