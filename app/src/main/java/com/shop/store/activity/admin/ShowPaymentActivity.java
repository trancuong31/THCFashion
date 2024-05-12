package com.shop.store.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.shop.store.adapter.AdminBillAdapter;
import com.shop.store.databinding.ActivityShowPaymentBinding;
import com.shop.store.dbhelper.BillDbHelper;
import com.shop.store.model.Bill;

import java.util.ArrayList;

public class ShowPaymentActivity extends AppCompatActivity {

    private ActivityShowPaymentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BillDbHelper billDbHelper = new BillDbHelper(this);
        ArrayList<Bill> bills = billDbHelper.getAllBills();
        AdminBillAdapter adapter = new AdminBillAdapter(this, bills);

        binding.listview.setAdapter(adapter);
        binding.listview.setLayoutManager(new LinearLayoutManager(this));

        binding.btnBack.setOnClickListener(view -> {
            finish();
        });
    }
}