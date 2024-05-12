package com.shop.store.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.shop.store.databinding.AdminDiscountListviewBinding;
import com.shop.store.model.DiscountStatus;
import com.shop.store.model.Discount;

import java.util.ArrayList;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.Viewholder> {
    private Context context;
    private ArrayList<Discount> stores;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(AdminDiscountListviewBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        try {
            DiscountStatus discountStatus = new Gson().fromJson(stores.get(position).getStatus(), DiscountStatus.class);
            holder.adminDiscountListviewBinding.txtRowShowIdDiscount.setText(stores.get(position).getId().toString());
            holder.adminDiscountListviewBinding.txtRowShowProductIdDiscount.setText(stores.get(position).getProductId().toString());
            holder.adminDiscountListviewBinding.txtRowShowValueDiscount.setText(stores.get(position).getValue().toString());
            holder.adminDiscountListviewBinding.txtRowStarttime.setText(!TextUtils.isEmpty(discountStatus.getStartTime())
                    ? discountStatus.getStartTime() : "");
            holder.adminDiscountListviewBinding.txtRowEndtime.setText(!TextUtils.isEmpty(discountStatus.getEndTime())
                    ? discountStatus.getEndTime() : "");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    static class Viewholder extends RecyclerView.ViewHolder {
        private AdminDiscountListviewBinding adminDiscountListviewBinding;

        public Viewholder(@NonNull AdminDiscountListviewBinding itemView) {
            super(itemView.getRoot());
            this.adminDiscountListviewBinding = itemView;
        }
    }

    public DiscountAdapter(Context context, ArrayList<Discount> objects) {
        this.context = context;
        this.stores = objects;
    }
}
