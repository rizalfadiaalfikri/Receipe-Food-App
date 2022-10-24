package com.rizalfadiaalfikri.receipefood.Screens.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rizalfadiaalfikri.receipefood.R;

public class SignUpActivity extends AppCompatActivity {

    private Button btn_signUp;
    private EditText ed_email, ed_password, ed_fullname;
    private TextView txt_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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

    }
}