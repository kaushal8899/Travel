package com.example.travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.travel.R;
import com.example.travel.model.User;
import com.example.travel.netutil.Network;
import com.example.travel.netutil.NetworkTask;
import com.example.travel.util.SharedUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity implements Network {
    TextInputEditText ed1;
    TextInputEditText ed2;
    Gson gson = new Gson();
    CircularProgressButton login;

    TextInputLayout ly2;
    SharedUtil sharedUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.sharedUtil = new SharedUtil(this);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        if (this.sharedUtil.readdata("islogin", "no").equals("true")) {
            startActivity(new Intent(this, MainActivity.class).putExtra("user", (User) this.gson.fromJson(this.sharedUtil.readdata("user_details", "null"), User.class)));
            overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
            finish();
        }
        this.login = (CircularProgressButton) findViewById(R.id.cirLoginButton);
        this.ed1 = (TextInputEditText) findViewById(R.id.editTextEmail);
        this.ed2 = (TextInputEditText) findViewById(R.id.editTextPassword);
        this.ly2 = (TextInputLayout) findViewById(R.id.textInputPassword);
        this.login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Logging in..", Toast.LENGTH_SHORT).show();
                LoginActivity.this.login();
            }
        });
        this.ed2.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (!LoginActivity.this.ly2.isPasswordVisibilityToggleEnabled()) {
                    TextInputLayout textInputLayout = LoginActivity.this.ly2;
                    boolean z = true;
                    if (s.length() <= 1) {
                        z = false;
                    }
                    textInputLayout.setPasswordVisibilityToggleEnabled(z);
                }
            }
        });
    }
    public void changeToReg(View View) {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
    }

    public void login() {
        this.login.startAnimation();
        String email = this.ed1.getText().toString();
        String password = this.ed2.getText().toString();
        String str = "Required...";
        if (TextUtils.isEmpty(email)) {
            this.ed1.setError(str);
            this.ed1.requestFocus();
            this.login.revertAnimation();
        } else if (TextUtils.isEmpty(password)) {
            this.ed2.setError(str);
            this.ed2.requestFocus();
            this.login.revertAnimation();
        } else {
            NetworkTask networkTask = new NetworkTask(this, this, null);
            StringBuilder sb = new StringBuilder();
            sb.append(getResources().getString(R.string.ip));
            sb.append("/login/");
            sb.append(email);
            sb.append("/");
            sb.append(password);
            networkTask.execute(new String[]{sb.toString()});
        }
    }

    public void postTask(final String data) {
        if (data == null || data.equals("-1")) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    LoginActivity.this.login.revertAnimation();
                    Toast.makeText(LoginActivity.this, "Invalid Username or Password\n Try Again.", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
            return;
        }
        final User u = this.gson.fromJson(data, User.class);
        if (!u.getIsvalid().equals("true") || !u.getType().equals("user")) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    LoginActivity.this.login.revertAnimation();
                    Toast.makeText(LoginActivity.this, "Your account is deactivated by Admin.", Toast.LENGTH_SHORT).show();
                }
            }, 2000);
        } else {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    LoginActivity.this.login.revertAnimation();
                    LoginActivity.this.sharedUtil.writedata("islogin", "true");
                    LoginActivity.this.sharedUtil.writedata("user_details", data);
                    LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("user", u));
                    LoginActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                    LoginActivity.this.finish();
                }
            }, 1500);
        }
    }

    public void forgetPassword(View view) {
    }
}
