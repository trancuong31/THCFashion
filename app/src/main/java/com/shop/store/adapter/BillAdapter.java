package com.shop.store.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.shop.store.R;
import com.shop.store.activity.FeedBackActivity;
import com.shop.store.dbhelper.ProductDbHelper;
import com.shop.store.model.JsonStatusCart;
import com.shop.store.model.Bill;
import com.shop.store.model.Cart;
import com.shop.store.model.Product;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private ArrayList<Bill> bills;
    private View view;
    private Context context;

    public BillAdapter(ArrayList<Bill> bills, Context context) {
        this.bills = bills;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        this.view = inflater.inflate(R.layout.bill_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bill bill = bills.get(position);
        Cart cart = bill.getCart();
        Integer productId = cart.getProductId();
        ProductDbHelper productDbHelper = new ProductDbHelper(context);
        Product product = productDbHelper.getProductById(productId);
        String[] json = bill.getDate().split(",");
        JsonStatusCart jsonStatusCart = new Gson().fromJson( json[1]+","+json[2]+","+json[3], JsonStatusCart.class);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.productImage);

        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice().toString() + "đ");
        holder.billQuantity.setText(cart.getQuantity().toString());
        holder.billTotalPrice.setText(bill.getPrice().toString());
        holder.billDeliveryAddress.setText(bill.getAddress().split(",")[0]);
        holder.billTime.setText(json[0]);
        holder.txtColor.setText("Color: " + jsonStatusCart.getColor());
        holder.txtSize.setText("Size: " + jsonStatusCart.getSize());
        holder.billDiscount.setText(bill.getDiscount().toString() + "%");
        String status = bill.getStatus();
        if (status.equals(Bill.BILL_UNPAID))
            holder.billStatus.setText("Chưa thanh toán");
        else if (status.equals(Bill.BILL_PAID))
            holder.billStatus.setText("Đã thanh toán");
        holder.itemView.setOnClickListener(v -> {
            if (status.equals(Bill.BILL_PAID) && bill.getAddress().split(",")[1].equals("false")) {
                Intent intent = new Intent(context, FeedBackActivity.class);
                intent.putExtra("Bill", bill);
                intent.putExtra("Product", product);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (bills == null) ? 0 : bills.size();
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView billQuantity;
        TextView billTotalPrice;
        TextView billDeliveryAddress;
        TextView billStatus;
        TextView billTime;
        TextView billDiscount, txtColor, txtSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.billProductImage);
            productName = itemView.findViewById(R.id.billProductName);
            productPrice = itemView.findViewById(R.id.productPrice);
            billQuantity = itemView.findViewById(R.id.billQuantity);
            billTotalPrice = itemView.findViewById(R.id.billTotalPrice);
            billDeliveryAddress = itemView.findViewById(R.id.billDeliveryAddress);
            billStatus = itemView.findViewById(R.id.billStatus);
            billTime = itemView.findViewById(R.id.billTime);
            billDiscount = itemView.findViewById(R.id.billDiscount);
            txtColor = itemView.findViewById(R.id.txtColor);
            txtSize = itemView.findViewById(R.id.txtSize);
        }
    }
}
