package com.shop.store.activity;

import static com.shop.store.utilities.AccountSessionManager.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.shop.store.MainActivity;
import com.shop.store.R;
import com.shop.store.common.Contants;
import com.shop.store.databinding.ActivityFeedBackBinding;
import com.shop.store.dbhelper.BillDbHelper;
import com.shop.store.model.Comment;
import com.shop.store.model.Star;
import com.shop.store.model.Bill;
import com.shop.store.model.Product;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FeedBackActivity extends AppCompatActivity {
    private ActivityFeedBackBinding binding;
    private Bill bill;
    private Product product;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase1;
    private String kichThuoc;
    private int star = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBackBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase1 = FirebaseDatabase.getInstance().getReference("Star");

        if (intent != null) {
            bill = (Bill) intent.getParcelableExtra("Bill");
            product = intent.getParcelableExtra("Product");
        }


        if (product.getProductImages().size() > 0) {
            Glide.with(this).load(product.getProductImages().get(0)).into(binding.imgProduct);
        }

        binding.star1.setOnClickListener(v -> {
            binding.star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            star = 1;
            binding.star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
            binding.star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
            binding.star4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
            binding.star5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));

        });
        binding.star2.setOnClickListener(v -> {
            binding.star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
            binding.star4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
            binding.star5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
            star = 2;
        });
        binding.star3.setOnClickListener(v -> {
            star = 3;
            binding.star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
            binding.star5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
        });
        binding.star4.setOnClickListener(v -> {
            star = 4;
            binding.star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_outline_24, null));
        });
        binding.star5.setOnClickListener(v -> {
            star = 5;
            binding.star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
            binding.star5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
        });

        binding.txtNameProduct.setText(product.getName());
        binding.txtSend.setOnClickListener(v -> {
            if (binding.rdChuan.isChecked()) {
                kichThuoc = "Chuẩn";
            } else if (binding.rdChat.isChecked()) {
                kichThuoc = "Chật";
            } else {
                kichThuoc = "Rộng";
            }

            String comment = "Chất liệu: ".concat(binding.edtChatLieu.getText().toString()).concat("\nMàu sắc: ").concat(binding.edtMauSac.getText().toString()).concat("\nMô tả: ").concat(binding.edtMoTa.getText().toString()).concat("\nKích thước: ").concat(kichThuoc.toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String id = mDatabase.push().getKey();
            Comment comment1 = new Comment(id, user.getFullname(), product.getId(), user.getAvatar(), comment, simpleDateFormat.format(new Date()), user.getId());
            mDatabase.child(Contants.COMMENT).child(id).setValue(comment1).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String idStar = mDatabase1.push().getKey();
                    Star starObj = new Star(idStar, product.getId(), star);
                    mDatabase1.child(idStar).setValue(starObj);

                    String address = bill.getAddress().split(",")[0].concat(",true");
                    BillDbHelper billDbHelper = new BillDbHelper(this);
                    bill.setAddress(address);
                    billDbHelper.update(bill);
                    Intent intent111 = new Intent(FeedBackActivity.this, MainActivity.class);
                    startActivity(intent111);
                    FeedBackActivity.this.finish();
                }
            });


        });

        binding.imgBack.setOnClickListener(v -> onBackPressed());

    }


}