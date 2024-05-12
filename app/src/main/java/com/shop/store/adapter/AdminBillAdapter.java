package com.shop.store.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shop.store.activity.admin.DetailsBillActivity;
import com.shop.store.activity.admin.ShowPaymentActivity;
import com.shop.store.databinding.AdminPaymentListviewBinding;
import com.shop.store.dbhelper.BillDbHelper;
import com.shop.store.dbhelper.UserDbHelper;
import com.shop.store.model.Bill;

import java.util.ArrayList;

public class AdminBillAdapter extends RecyclerView.Adapter<AdminBillAdapter.Viewholder> {
    Activity context;
    ArrayList<Bill> bills;
    int layoutResource;

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(AdminPaymentListviewBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String billId = bills.get(position).getId().toString();
        holder.binding.txtRowShowIdPayment.setText("Mã đơn hàng: " + billId);

        BillDbHelper billDbHelper = new BillDbHelper(context);
        UserDbHelper userDbHelper = new UserDbHelper(context);

        String userId = bills.get(position).getUserId().toString();
        String userName = userDbHelper.getUser(Integer.parseInt(userId)).getFullname();
        holder.binding.txtRowShowNameUser.setText("Họ và tên người dùng: " + userName);

        if (bills.get(position).getStatus().equals("Paid")) {
            holder.binding.buttonConfirm.setVisibility(View.GONE);
        }else{
            holder.binding.buttonConfirm.setVisibility(View.VISIBLE);
        }

        holder.binding.buttonConfirm.setOnClickListener(view -> {
            Bill bill = billDbHelper.getBillById(Integer.parseInt(billId));
            bill.setStatus("Paid");
            billDbHelper.update(bill);
            Intent intent = new Intent(context, ShowPaymentActivity.class);
            context.startActivity(intent);
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailsBillActivity.class);
            intent.putExtra("id", bills.get(position).getId());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private AdminPaymentListviewBinding binding;

        public Viewholder(@NonNull AdminPaymentListviewBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public AdminBillAdapter(Activity context, ArrayList<Bill> objects) {
        this.context = context;
        this.bills = objects;
    }


}
