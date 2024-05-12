package com.shop.store.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shop.store.adapter.AdapterChatUser;
import com.shop.store.adapter.AdapterMessage;
import com.shop.store.databinding.ActivityChatBinding;
import com.shop.store.model.Chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {
    private ActivityChatBinding binding;
    private DatabaseReference mDatabase;
    private String type = "";
    private String name = "";
    private String image = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase = FirebaseDatabase.getInstance().getReference("Chat");
        if (getIntent() != null) {
            type = getIntent().getStringExtra("type");
            name = getIntent().getStringExtra("name");
            image = getIntent().getStringExtra("image");
        }

        binding.imgBack.setOnClickListener(v -> finish());

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Chat> arrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Chat comment = ds.getValue(Chat.class);
                    arrayList.add(comment);
                }

                ArrayList<Chat> arrayList11 = new ArrayList<>();
                for (Chat chat : arrayList) {
                    if (chat.getUser().equals(name)) {
                        arrayList11.add(chat);
                    }
                }
                if (type.equals("0")) {
                    AdapterChatUser adapterComment = new AdapterChatUser(ChatActivity.this, arrayList11, type);
                    binding.rclChat.setAdapter(adapterComment);
                    binding.rclChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                } else {
                    AdapterMessage adapterComment = new AdapterMessage(ChatActivity.this, arrayList11, type);
                    binding.rclChat.setAdapter(adapterComment);
                    binding.rclChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.addValueEventListener(postListener);


        binding.imgSend.setOnClickListener(v -> {
            String key = mDatabase.push().getKey();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
            Chat chat = new Chat(name, key, binding.edtMessage.getText().toString(),
                    simpleDateFormat.format(new Date()), image, "", type);
            mDatabase.child(key).setValue(chat).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) binding.edtMessage.setText("");
                }
            });
        });

    }
}