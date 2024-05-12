package com.shop.store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shop.store.common.BaseViewHolder;
import com.shop.store.databinding.ItemCommentBinding;
import com.shop.store.model.Comment;

import java.util.ArrayList;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.ViewHolder> {

    private Context context;
    private ArrayList<Comment> comments;

    public AdapterComment(Context context, ArrayList<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);
        holder.itemCommentBinding.txtUserNameCommnet.setText(comment.getUsername());
        holder.itemCommentBinding.txtCommentDate.setText(comment.getDate());
        holder.itemCommentBinding.txtComment.setText(comment.getComment());
        Glide.with(context)
                .load(comment.getImage())
                .into(holder.itemCommentBinding.imgUserComment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class ViewHolder extends BaseViewHolder<ItemCommentBinding> {
        private ItemCommentBinding itemCommentBinding;

        public ViewHolder(@NonNull ItemCommentBinding itemView) {
            super(itemView);
            this.itemCommentBinding = itemView;
        }
    }
}
