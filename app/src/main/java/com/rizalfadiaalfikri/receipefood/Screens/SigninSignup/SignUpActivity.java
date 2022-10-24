package com.rizalfadiaalfikri.receipefood.Screens.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Home.HomeActivity;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private Button btn_signUp;
    private EditText ed_email, ed_password, ed_fullname;
    private TextView txt_signIn;

    private ApiInterface apiInterface;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        apiInterface = ApiClient.getClientApi().create(ApiInterface.class);

        initializefields();

    }

    private void initializefields() {

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        ed_fullname = findViewById(R.id.ed_fullname);
        btn_signUp = findViewById(R.id.btn_signUp);
        txt_signIn = findViewById(R.id.txt_signIn);

        txt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();
            }
        });

    }

    private void registration() {

        String user_name = ed_fullname.getText().toString();
        String user_email = ed_email.getText().toString();
        String user_password = ed_password.getText().toString();

        if (TextUtils.isEmpty(user_name)) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(user_email)) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(user_password)) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
        } else {
            ProgressDialog dialog = new ProgressDialog(SignUpActivity.this);
            dialog.setTitle("Sign Up .....");
            dialog.setMessage("please wait while are adding your credentials");
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);

            Call<Users> call = apiInterface.registration(user_name, user_email, user_password);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.body().getResponse().equals("ok")) {
                        user_id = response.body().getUser_id();

                        Intent intent = new Intent(SignUpActivity.this, HomeActivity.class);
                        startActivity(intent);

                        Toast.makeText(SignUpActivity.this, "Account created successfull", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    } else if (response.body().getResponse().equals("failed")) {
                        Toast.makeText(SignUpActivity.this, "something went wrong, please try again", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else if (response.body().getResponse().equals("already")) {
                        Toast.makeText(SignUpActivity.this, "this Email already exist, please try another", Toast.LENGTH_SHORT).show();
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