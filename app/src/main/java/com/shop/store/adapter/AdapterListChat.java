package com.shop.store.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shop.store.activity.ChatActivity;
import com.shop.store.databinding.ItemListChatBinding;
import com.shop.store.model.ListPerson;

import java.util.List;

public class AdapterListChat extends RecyclerView.Adapter<AdapterListChat.Viewholder> {
    private Context context;
    private List<ListPerson> list;
    private IOnClickListener IOnClickListener;
    private IOnDeleteListener iOnDeleteListener;

    public interface IOnClickListener {
        void click();
    }


    public AdapterListChat(Context context, List<ListPerson> list, IOnClickListener IOnClickListener, IOnDeleteListener i) {
        this.context = context;
        this.list = list;
        this.iOnDeleteListener = i;
        this.IOnClickListener = IOnClickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(ItemListChatBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getImg())
                .into(holder.binding.imgCircle);

        holder.binding.txtName.setText(list.get(position).getEmail());
        holder.itemView.setOnClickListener(v -> {
            IOnClickListener.click();
            Intent intent = new Intent(context, ChatActivity.class);
            intent.putExtra("name", list.get(position).getEmail());
            intent.putExtra("image", list.get(position).getImg());
            intent.putExtra("type", "1");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        holder.binding.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnDeleteListener.onDelete(list.get(position).getEmail());
            }
        });
    }

    public interface IOnDeleteListener {
        void onDelete(String name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private ItemListChatBinding binding;

        public Viewholder(@NonNull ItemListChatBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
