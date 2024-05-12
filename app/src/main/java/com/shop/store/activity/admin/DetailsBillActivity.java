package com.shop.store.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.shop.store.databinding.ActivityDetailsBillBinding;
import com.shop.store.dbhelper.BillDbHelper;
import com.shop.store.dbhelper.CartDbHelper;
import com.shop.store.dbhelper.ProductDbHelper;
import com.shop.store.model.JsonStatusCart;
import com.shop.store.model.Bill;
import com.shop.store.model.Cart;
import com.shop.store.model.Product;

public class DetailsBillActivity extends AppCompatActivity {
    private ActivityDetailsBillBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int idBill = getIntent().getIntExtra("id", 0);

        BillDbHelper billDbHelper = new BillDbHelper(this);
        Bill bill = billDbHelper.getBillById(idBill);
        ProductDbHelper productDbHelper = new ProductDbHelper(this);
        CartDbHelper cartDbHelper = new CartDbHelper(this);
        Cart cart = cartDbHelper.getCartByRowId(bill.getCartId());
        Product product = productDbHelper.getProductById(cart.getProductId());

        binding.txtNameProduct.setText(product.getName());

        if (product.getProductImages() != null && product.getProductImages().size() > 0)
            Glide.with(this)
                    .load(product.getProductImages().get(0))
                    .into(binding.imgProduct);

        String[] json = bill.getDate().split(",");
        JsonStatusCart jsonStatusCart = new Gson().fromJson( json[1]+","+json[2]+","+json[3], JsonStatusCart.class);

        binding.txtPriceProduct.setText(product.getPrice() + " VND");
        binding.txtNumberProduct.setText(cart.getQuantity() != null ? "Quantity: " + cart.getQuantity().toString() : "0");
        binding.txtAddress.setText("Address: " + bill.getAddress().split(",")[0]);
        binding.txtPhone.setText("Phone: " + bill.getPhone());

        binding.txtDate.setText("Date: " + json[0]);
        binding.txtColor.setText("Color: " + jsonStatusCart.getColor());
        binding.txtSize.setText("Size: " + jsonStatusCart.getSize());

        binding.txtDiscount.setText(bill.getDiscount() != null ? "Discount: " + bill.getDiscount().toString() : "Discount: " + "0");
        binding.txtStatus.setText("Status: " + bill.getStatus().split(",")[0]);

        binding.imgBack.setOnClickListener(v->finish());
    }
}