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
import android.widget.EditText;
import android.widget.ImageView;

import com.shop.store.R;
import com.shop.store.adapter.AdminUserAdapter;
import com.shop.store.dbhelper.UserDbHelper;
import com.shop.store.model.User;

import java.util.ArrayList;
import java.util.Collections;

public class ShowAdUserActivity extends AppCompatActivity {
    private RecyclerView listView;
    private ImageView back;
    private ImageView imgSort;
    private EditText edtSearch;
    private AdminUserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ad_user);

        imgSort = findViewById(R.id.imgSort);
        edtSearch = findViewById(R.id.edtSearch);

        UserDbHelper userDbHelper = new UserDbHelper(this);
        ArrayList<User> user = userDbHelper.getAllUser();
        adapter = new AdminUserAdapter(this, user);
        listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));

        imgSort.setOnClickListener(v -> {
            Collections.sort(user, (o1, o2) -> o2.getFullname().compareTo(o1.getFullname()));
            adapter = new AdminUserAdapter(ShowAdUserActivity.this, user);
            listView = findViewById(R.id.listview);
            listView.setAdapter(adapter);
            listView.setLayoutManager(new LinearLayoutManager(this));
        });

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
                    ArrayList list = new ArrayList<User>();
                    for (User name : user) {
                        if (name.getFullname().toLowerCase().contains(s.toString().toLowerCase())) {
                            list.add(name);
                        }
                    }
                    adapter = new AdminUserAdapter(ShowAdUserActivity.this, list);
                    listView.setAdapter(adapter);
                    listView.setLayoutManager(new LinearLayoutManager(ShowAdUserActivity.this));
                }
            }
        });

        back = findViewById(R.id.btnBackToDashboard);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowAdUserActivity.this, DashBoard.class);
                startActivity(intent);
            }
        });

    }
}