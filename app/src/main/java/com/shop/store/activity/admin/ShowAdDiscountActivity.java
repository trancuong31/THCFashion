package com.shop.store.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.shop.store.R;
import com.shop.store.adapter.DiscountAdapter;
import com.shop.store.dbhelper.DiscountDbHelper;
import com.shop.store.model.Discount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ShowAdDiscountActivity extends AppCompatActivity {

    RecyclerView listView;
    ImageView back;
    Button crud;
    private ImageView imgSort;
    private DiscountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ad_discount);

        imgSort = findViewById(R.id.imgSort);

        DiscountDbHelper discountDbHelper = new DiscountDbHelper(this);
        ArrayList<Discount> discounts = discountDbHelper.getAllDiscounts();
        adapter = new DiscountAdapter(this, discounts);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        imgSort.setOnClickListener(v -> {
            Collections.sort(discounts, Comparator.comparing(Discount::getValue));
            adapter = new DiscountAdapter(this, discounts);
            listView = findViewById(R.id.listview);
            listView.setAdapter(adapter);
            listView.setLayoutManager(new LinearLayoutManager(this));
        });

        back = findViewById(R.id.btnBackToDashboard);
        back.setOnClickListener(view -> {
            Intent intent = new Intent(ShowAdDiscountActivity.this, DashBoard.class);
            startActivity(intent);
        });

        crud = findViewById(R.id.btnCRUDDiscount);
        crud.setOnClickListener(view -> {
            Intent intent = new Intent(ShowAdDiscountActivity.this, AdminDiscountActivity.class);
            startActivity(intent);
        });
    }
}