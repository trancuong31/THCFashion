package com.shop.store.activity.admin;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shop.store.R;
import com.shop.store.adapter.AdapterListChat;
import com.shop.store.databinding.ActivityListChatBinding;
import com.shop.store.model.Chat;
import com.shop.store.model.ListPerson;

import java.util.ArrayList;

public class ListChatActivity extends AppCompatActivity implements AdapterListChat.IOnClickListener, AdapterListChat.IOnDeleteListener {
    private ActivityListChatBinding binding;
    private DatabaseReference mDatabase;
    private ArrayList<Chat> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Chat comment = ds.getValue(Chat.class);
                    arrayList.add(comment);
                }

                ArrayList<ListPerson> listPeople = new ArrayList<>();
                for (Chat chat : arrayList) {
                    if (!listPeople.stream().anyMatch(s -> s.getEmail().equals(chat.getUser()))) {
                        listPeople.add(new ListPerson(chat.getUser(), chat.getAvatarUser()));
                    }
                }

                AdapterListChat adapterListChat = new AdapterListChat(getApplicationContext(), listPeople, ListChatActivity.this, ListChatActivity.this);
                binding.rclListChat.setAdapter(adapterListChat);
                binding.rclListChat.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase = FirebaseDatabase.getInstance().getReference("Chat");
        mDatabase.addValueEventListener(postListener);


        binding.imgBack.setOnClickListener(v->finish());

    }

    @Override
    public void click() {

    }

    @Override
    public void onDelete(String name) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ListChatActivity.this);
        builder1.setMessage("Bạn có muốn xóa chat hay không?");
        builder1.setIcon(ResourcesCompat.getDrawable(getResources(),R.drawable.delete,null));
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Xóa",
                (dialog, id) -> {
                    for (Chat chat : arrayList) {
                        if (chat.getUser().equals(name)) {
                            mDatabase = FirebaseDatabase.getInstance().getReference("Chat")
                                    .child(chat.getId());
                            mDatabase.removeValue();
                        }
                    }
                    dialog.cancel();
                });

        builder1.setNegativeButton(
                "Hủy",
                (dialog, id) -> dialog.cancel());

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
}