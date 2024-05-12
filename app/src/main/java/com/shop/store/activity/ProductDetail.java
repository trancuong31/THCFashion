package com.shop.store.activity;

import static com.shop.store.utilities.AccountSessionManager.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.shop.store.R;
import com.shop.store.adapter.AdapterComment;
import com.shop.store.common.Contants;
import com.shop.store.dbhelper.AccountDbHelper;
import com.shop.store.dbhelper.CartDbHelper;
import com.shop.store.dbhelper.ProductDbHelper;
import com.shop.store.model.Comment;
import com.shop.store.model.JsonDataDetails;
import com.shop.store.model.JsonStatusCart;
import com.shop.store.model.Star;
import com.shop.store.model.Account;
import com.shop.store.model.Cart;
import com.shop.store.model.Product;
import com.shop.store.model.Store;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetail extends AppCompatActivity {

    public static final String PRODUCT_ID = "productId";
    private Product product;
    private Cart cart;
    private int quantity = 0;
    private DatabaseReference mDatabase;

    @SuppressLint("NonConstantResourceId")
    @BindView(value = R.id.productImage)
    ImageView productImage;
    @SuppressLint("NonConstantResourceId")
    @BindView(value = R.id.btnAddCart)
    Button btnAddCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(value = R.id.btnViewCart)
    Button btnViewCart;
    @SuppressLint("NonConstantResourceId")
    @BindView(value = R.id.subtract)
    ImageButton btnSubtract;
    @SuppressLint("NonConstantResourceId")
    @BindView(value = R.id.plus)
    ImageButton btnPlus;
    @SuppressLint("NonConstantResourceId")
    @BindView(value = R.id.txtQuantity)
    TextView txtQuantity;
    @SuppressLint("NonConstantResourceId")
//    @BindView(value = R.id.svReview)
//    ScrollView svReview;
    @BindView(R.id.txtColorDetails)
    TextView txtColorDetails;
    @BindView(R.id.txtSizeDetails)
    TextView txtSizeDetails;
    @BindView(R.id.btnComment)
    FloatingActionButton btnComment;
    @BindView(R.id.rclComment)
    RecyclerView rclComment;
    @BindView(R.id.star1)
    ImageView star1;
    @BindView(R.id.star2)
    ImageView star2;
    @BindView(R.id.star3)
    ImageView star3;
    @BindView(R.id.star4)
    ImageView star4;
    @BindView(R.id.star5)
    ImageView star5;

    @BindView(R.id.spinerColor)
    Spinner spinnerColor;

    @BindView(R.id.spinerSize)
    Spinner spinnerSize;

    private String color, size;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);

        ButterKnife.bind(this);
        this.quantity = 1;
        txtQuantity.setText("1");
        btnViewCart.setVisibility(View.GONE);

        findViewById(R.id.btnBack_detail).setOnClickListener(v -> {
            finish();
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();

        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    color = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                size = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setProduct();
        btnSubtract.setOnClickListener(this::setSubtractQuantity);
        btnPlus.setOnClickListener(this::setAddQuantity);
        btnAddCart.setOnClickListener(this::setAddCart);
        btnViewCart.setOnClickListener(this::setViewCart);
        btnComment.setOnClickListener(v -> showDialogComment());

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Comment> arrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Comment comment = ds.getValue(Comment.class);
                    if (comment.getProductId() == product.getId()) {
                        arrayList.add(comment);
                    }
                }

                AdapterComment adapterComment = new AdapterComment(ProductDetail.this, arrayList);
                rclComment.setAdapter(adapterComment);
                rclComment.setLayoutManager(new LinearLayoutManager(ProductDetail.this));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.child(Contants.COMMENT).addValueEventListener(postListener);

        ValueEventListener post = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Star> arrayList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Star star = ds.getValue(Star.class);
                    if (star.getIdProduct() == product.getId()) {
                        arrayList.add(star);
                    }
                }
                int total = 0;
                for (Star star : arrayList) {
                    total += star.getNumberStar();
                }

                int startNumber = arrayList != null && arrayList.size() > 0 ? total / arrayList.size() : 0;
                if (startNumber == 0) return;
                if (startNumber == 1) {
                    star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                } else if (startNumber == 2) {
                    star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                } else if (startNumber == 3) {
                    star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                } else if (startNumber == 4) {
                    star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                } else {
                    star1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                    star5.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_star_24, null));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabase.child("Star").addValueEventListener(post);

    }

    private void showDialogComment() {
        Intent intent = new Intent(ProductDetail.this, ChatActivity.class);
        AccountDbHelper accountDbHelper = new AccountDbHelper(this);
        Account account = accountDbHelper.getAccount(user.getAccountId());
        intent.putExtra("type", "0");
        intent.putExtra("name", account.getEmail());
        intent.putExtra("image", user.getAvatar());
        startActivity(intent);
    }

    private void setViewCart(View view) {
        Intent intent = new Intent(view.getContext(), CartDetail.class);
        CartDetail.cart = this.cart;
        view.getContext().startActivity(intent);
        finish();
    }

    private void setAddCart(View view) {
        if (this.quantity <= 0) {
            Toast.makeText(this, "Số lượng sản phẩm tối thiểu là 1.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (user != null) {
            if (Integer.parseInt(product.getStatus()) < quantity) {
                Toast.makeText(this, "Đặt quá số lượng", Toast.LENGTH_LONG).show();
            } else {
                JsonStatusCart jsonStatusCart = new JsonStatusCart(Cart.CART_UNPAID,color,size);
                CartDbHelper cartDbHelper = new CartDbHelper(this);
                Cart cart = new Cart(
                        user.getId(),
                        this.product.getId(),
                        this.quantity, new Gson().toJson(jsonStatusCart));
                long rowId = cartDbHelper.insert(cart);
                if (rowId > 0) {
                    Toast.makeText(this, "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
                    this.cart = cart;
                    this.cart.setId(cartDbHelper.getCartIdByRowId(rowId));
                    btnAddCart.setVisibility(View.GONE);
                    btnViewCart.setVisibility(View.VISIBLE);
                } else
                    Toast.makeText(this, "Đã xảy ra lỗi khi thêm giỏ hàng.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
    }

    private void setAddQuantity(View view) {
        this.quantity += 1;
        txtQuantity.setText(String.valueOf(quantity));
    }

    private void setSubtractQuantity(View view) {
        if (this.quantity <= 0) {
            this.quantity = 0;
            txtQuantity.setText("0");
        } else {
            quantity -= 1;
            txtQuantity.setText(String.valueOf(quantity));
        }
    }

    private void setProduct() {
        Bundle bundle = getIntent().getExtras();
        ProductDbHelper productDbHelper = new ProductDbHelper(this);
        this.product = productDbHelper.getProductById((int) bundle.getLong(PRODUCT_ID));

        if (this.product != null) {
            setProductImage();
            setProductTitle();
            setProductDetail();
            setProductStore(productDbHelper);
            //svReview.setVisibility(View.GONE);
        }
    }

    private void setProductImage() {
        if (product.getProductImages() != null) {
            if (product.getProductImages().size() > 0)
                Glide.with(this)
                        .load(product.getProductImages().get(0))
                        .into(productImage);
        }
    }

    private void setProductTitle() {
        ((TextView) findViewById(R.id.productTitle)).setText(this.product.getName());
        ((TextView) findViewById(R.id.productPrice)).setText(product.getPrice().toString());
    }

    private void setProductDetail() {
        String productDetail = product.getDetail();
        TextView tvProductDetail = findViewById(R.id.productDescription);
        if (productDetail == null)
            tvProductDetail.setVisibility(TextView.GONE);
        else {
            JsonDataDetails jsonDataDetails = new Gson().fromJson(productDetail, JsonDataDetails.class);
            findViewById(R.id.txtTitlelDescription).setVisibility(!TextUtils.isEmpty(jsonDataDetails.getDetails()) ? View.VISIBLE : View.GONE);
            tvProductDetail.setVisibility(!TextUtils.isEmpty(jsonDataDetails.getDetails()) ? View.VISIBLE : View.GONE);
            txtColorDetails.setVisibility(!TextUtils.isEmpty(jsonDataDetails.getColor()) ? View.VISIBLE : View.GONE);
            txtSizeDetails.setVisibility(!TextUtils.isEmpty(jsonDataDetails.getSize()) ? View.VISIBLE : View.GONE);

            tvProductDetail.setText(jsonDataDetails.getDetails());
            //  txtColorDetails.setText("Color: " + jsonDataDetails.getColor());
            String[] color = jsonDataDetails.getColor().split(",");
            ArrayAdapter aa11 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, color);
            aa11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            Spinner spinner1 = findViewById(R.id.spinerColor);
            spinner1.setAdapter(aa11);

            //txtSizeDetails.setText("Size: " + jsonDataDetails.getSize());
            String[] size = jsonDataDetails.getSize().split(",");
            ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, size);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            Spinner spinner = findViewById(R.id.spinerSize);
            spinner.setAdapter(aa);

            TextView txtNumber = findViewById(R.id.productNumber);
            String number = !product.getStatus().equals("0") ? product.getStatus() : "hết hàng";
            txtNumber.setText("The remaining amount: " + number);
        }
    }

    private void setProductStore(ProductDbHelper productDbHelper) {
        Store store = productDbHelper.getStore(this.product.getStoreId());
        if (store != null) {
            ((TextView) findViewById(R.id.productStore)).setText(store.getName());
            ((TextView) findViewById(R.id.productStoreAddress)).setText(store.getAddress());
        }
    }
}