package com.shop.store.common;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public class BaseViewHolder<T extends ViewBinding> extends RecyclerView.ViewHolder {
    protected T viewHolder;
    public BaseViewHolder(@NonNull T itemView) {
        super(itemView.getRoot());
        this.viewHolder = itemView;
    }
}
