package com.shop.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shop.store.R;
import com.shop.store.model.Chat;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterMessage extends RecyclerView.Adapter<AdapterMessage.Viewholder> {
    private Context context;
    private ArrayList<Chat> list;
    private String typeUser = "0";
    private int typeAdmin = 1;

    public AdapterMessage(Context context, ArrayList<Chat> list, String typeUser) {
        this.context = context;
        this.list = list;
        this.typeUser = typeUser;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (viewType == 1) {
            View photoView = inflater.inflate(R.layout.item_admin, parent, false);
            return new Viewholder(photoView);
        } else {
            View photoView = inflater.inflate(R.layout.item_chat_user, parent, false);
            return new Viewholder(photoView);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txtMessage.setText(list.get(position).getMessage());
        if (holder.getItemViewType() == 1) {
            Glide.with(context)
                    .load(list.get(position).getAvatarAdmin())
                    .into(holder.imgPerson);
        } else {
            Glide.with(context)
                    .load(list.get(position).getAvatarUser())
                    .into(holder.imgPerson);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType().equals("0") ) {
            return 0;
        } else {
            return 1;
        }
    }

    class Viewholder extends RecyclerView.ViewHolder {
        TextView txtMessage;
        CircleImageView imgPerson;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txtMess);
            imgPerson = itemView.findViewById(R.id.imgPerson);
        }
    }
}
