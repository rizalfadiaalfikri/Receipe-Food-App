package com.rizalfadiaalfikri.receipefood.Screens.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rizalfadiaalfikri.receipefood.R;

public class SignInActivity extends AppCompatActivity {


    private Button btn_signIn;
    private EditText ed_email, ed_password;
    private TextView txt_signUp, txt_forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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

    }
}