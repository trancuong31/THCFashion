package com.shop.store.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.shop.store.R;
import com.shop.store.adapter.AdminProductAdapter;
import com.shop.store.dbhelper.ProductDbHelper;
import com.shop.store.model.Product;

import java.util.ArrayList;
import java.util.Collections;

public class ShowAdProductActivity extends AppCompatActivity {

    RecyclerView listView;
    ImageView back;
    Button crud;
    EditText edtSearch;
    ImageView imgSort;
AdminProductAdapter adminProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ad_product);
        imgSort = findViewById(R.id.imgSort);
        ProductDbHelper productDbHelper = new ProductDbHelper(this);
        ArrayList<Product> products = productDbHelper.getAllProducts();
        AdminProductAdapter adapter = new AdminProductAdapter(this, products);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        back = findViewById(R.id.btnBackToDashboard);
        edtSearch = findViewById(R.id.edtSearch);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s.toString())) {

                } else {
                    ArrayList list = new ArrayList<Product>();
                    for (Product name : products) {
                        if (name.getName().toLowerCase().contains(s.toString().toLowerCase())) {
                            list.add(name);
                        }
                    }
                    AdminProductAdapter adapter = new AdminProductAdapter(getApplicationContext(), list);
                    listView.setAdapter(adapter);
                    listView.setLayoutManager(new LinearLayoutManager(ShowAdProductActivity.this));
                }

            }
        });

        imgSort.setOnClickListener(v -> {
            Collections.sort(products, (o1, o2) -> o1.getPrice() >= o2.getPrice() ? 1 : -1);
            adminProductAdapter = new AdminProductAdapter(getApplicationContext(), products);
            listView.setAdapter(adapter);
            listView.setLayoutManager(new LinearLayoutManager(ShowAdProductActivity.this));
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAdProductActivity.this, DashBoard.class);
                startActivity(intent);
            }
        });

        crud = findViewById(R.id.btnCRUDProduct);
        crud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAdProductActivity.this, AdminProductActivity.class);
                startActivity(intent);
            }
        });
    }
}