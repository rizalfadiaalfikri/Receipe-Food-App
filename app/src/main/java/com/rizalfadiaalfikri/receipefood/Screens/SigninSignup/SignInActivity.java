package com.rizalfadiaalfikri.receipefood.Screens.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Home.HomeActivity;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.Users;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {


    private Button btn_signIn;
    private EditText ed_email, ed_password;
    private TextView txt_signUp, txt_forgotPassword;

    private ApiInterface apiInterface;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        apiInterface = ApiClient.getClientApi().create(ApiInterface.class);

        sessionManager = new SessionManager(SignInActivity.this);

        initializefields();

    }

    private void initializefields() {

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        txt_signUp = findViewById(R.id.txt_signUp);
        txt_forgotPassword = findViewById(R.id.txt_forgotPassword);
        btn_signIn = findViewById(R.id.btn_signIn);

        txt_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    private void login() {

        String user_email = ed_email.getText().toString();
        String user_password = ed_password.getText().toString();

        if (TextUtils.isEmpty(user_email)) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(user_password)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog dialog = new ProgressDialog(SignInActivity.this);
            dialog.setTitle("Sign In ......");
            dialog.setMessage("Please wait while we are checking your credentials");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.login(user_email, user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getResponse().equals("ok")) {
                        String user_id = response.body().getUser_id();
                        sessionManager.createSession(user_id);
                        Log.d("USER_ID", user_id);

                        Intent intent = new Intent(SignInActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(SignInActivity.this, "Sign In is Successfull", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else if (response.body().getResponse().equals("no_account")) {
                        Toast.makeText(SignInActivity.this, "No Account", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });
        }

    }
}