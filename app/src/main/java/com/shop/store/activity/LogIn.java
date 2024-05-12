package com.shop.store.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import com.shop.store.MainActivity;
import com.shop.store.R;
import com.shop.store.activity.admin.DashBoard;
import com.shop.store.model.Account;
import com.shop.store.model.User;
import com.shop.store.utilities.AccountSessionManager;

public class LogIn extends AppCompatActivity {
    Button btnLogin;
    TextInputEditText txtEmail;
    TextInputEditText txtPassword;
    TextView txtSignUp;
    Button btnForgotPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        addControl();
        addEvent();

    }

    private void addControl() {
        btnLogin = findViewById(R.id.btnLogIn);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.txtPassword);
        txtSignUp = findViewById(R.id.txtSignUp);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
    }

    private void addEvent() {
        findViewById(R.id.btnBack).setOnClickListener(view -> finish());
        btnLogin.setOnClickListener(this::setLogin);
        txtSignUp.setOnClickListener(this::setSignUp);
        btnForgotPassword.setOnClickListener(this::setForgotPassword);
    }

    private void setLogin(View view) {
        if (MainActivity.getInstation() != null) {
            MainActivity.getInstation().finish();
        }
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();
        if (email.equals("admin") && password.equals("123")) {
            AccountSessionManager.account = new Account(1, "admin", "admin", "123", 1, "");
            AccountSessionManager.user = new User(1, 1, "admin", null, null, null, null, null, "");
            SharedPreferences sharedPreferences = getSharedPreferences("admin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Admin", "true");
            editor.commit();
            Intent adminActivity = new Intent(this, DashBoard.class);
            startActivity(adminActivity);
            finish();
            return;
        } else {
            Intent intent = new Intent(this, FirebaseActivity.class);
            intent.putExtra(FirebaseActivity.EMAIL, email);
            intent.putExtra(FirebaseActivity.PASSWORD, password);
            intent.setAction(FirebaseActivity.SIGN_IN_ACTION);
            startActivityForResult(intent, FirebaseActivity.SIGN_IN);
        }
    }

    private void setSignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
        finish();
    }

    private void setForgotPassword(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivityForResult(intent, FirebaseActivity.FORGOT_PASSWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FirebaseActivity.SIGN_IN) {
            if (resultCode == FirebaseActivity.SIGN_IN_OK) {
                Log.e("TAG", "onActivityResult: ok1");
                if (AccountSessionManager.user != null) {
                    if (AccountSessionManager.account.getRoleID() == 1) {
                        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent adminActivity = new Intent(this, DashBoard.class);
                        startActivity(adminActivity);
                        finish();
                    } else if (AccountSessionManager.account.getRoleID() == 2
                            && AccountSessionManager.account.getStatus().equals("active")) {
                        Intent mainActivity = new Intent(this, MainActivity.class);
                        startActivity(mainActivity);
                        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        finish();
                        Log.e("TAG", "onActivityResult: ok");
                    } else {
                        Toast.makeText(this, "Tài khoản không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
