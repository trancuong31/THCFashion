package com.shop.store.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shop.store.databinding.AdminUserListviewBinding;
import com.shop.store.dbhelper.AccountDbHelper;
import com.shop.store.dbhelper.UserDbHelper;
import com.shop.store.model.Account;
import com.shop.store.model.User;

import java.util.ArrayList;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.Viewholder> {
    Activity context;
    ArrayList<User> users;

    public AdminUserAdapter(Activity context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(AdminUserListviewBinding.inflate(LayoutInflater.from(context),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.itembinding.txtRowShowIdUser.setText("Id: " + users.get(position).getId().toString());
        holder.itembinding.txtRowShowNameUser.setText("Fullname: " + users.get(position).getFullname());
        holder.itembinding.btnAction.setText(users.get(position).getStatus().equals("active") ? "active" : "unactive");

        holder.itembinding.btnAction.setOnClickListener(v -> {
            if (users.get(position).getStatus().equals("active")) {
                AccountDbHelper accountDbHelper = new AccountDbHelper(context);
                UserDbHelper userDbHelper = new UserDbHelper(context);
                Account account = accountDbHelper.getAccount(users.get(position).getAccountId());
                ArrayList<User> list = userDbHelper.getAllUser();

                for (User user : list) {
                    if (user.getFullname().equals(users.get(position).getFullname())) {
                        account.setStatus("unactive");
                        user.setStatus("unactive");
                        userDbHelper.update(user);
                        accountDbHelper.update(account);
                        users = userDbHelper.getAllUser();
                        notifyItemChanged(position);
                    }
                }
            } else {
                AccountDbHelper accountDbHelper = new AccountDbHelper(context);
                UserDbHelper userDbHelper = new UserDbHelper(context);
                Account account = accountDbHelper.getAccount(users.get(position).getAccountId());
                ArrayList<User> list = userDbHelper.getAllUser();

                for (User user : list) {
                    if (user.getFullname().equals(users.get(position).getFullname())) {
                        account.setStatus("active");
                        user.setStatus("active");
                        userDbHelper.update(user);
                        accountDbHelper.update(account);
                        users = userDbHelper.getAllUser();
                        notifyItemChanged(position);
                    }
                }
            }
        });

        holder.itembinding.imgDelete.setOnClickListener(v -> {
            AccountDbHelper accountDbHelper = new AccountDbHelper(context);
            UserDbHelper userDbHelper = new UserDbHelper(context);
            Account account = accountDbHelper.getAccount(users.get(position).getAccountId());
            ArrayList<User> list = userDbHelper.getAllUser();

            for (User user : list) {
                if (user.getFullname().equals(users.get(position).getFullname())) {
                    userDbHelper.delete(user);
                    accountDbHelper.delete(account);
                    users = userDbHelper.getAllUser();
                    delete(account.getEmail(), account.getPassword(), position);
                }
            }
        });

    }

    public void delete(String email, String pass, int position) {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential(email, pass);

        assert user != null;
        user.reauthenticate(credential)
                .addOnCompleteListener(task -> user.delete()
                        .addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                notifyItemChanged(position);
                                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                            }
                        }));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        private AdminUserListviewBinding itembinding;

        public Viewholder(@NonNull AdminUserListviewBinding itemView) {
            super(itemView.getRoot());
            this.itembinding = itemView;
        }
    }
}
