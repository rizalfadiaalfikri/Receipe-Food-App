package com.rizalfadiaalfikri.receipefood.Screens.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.rizalfadiaalfikri.receipefood.R;

public class SignInActivity extends AppCompatActivity {


    private Button btn_signIn;
    private EditText ed_email, ed_password;
    private TextView txt_signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initializefields();

    }

    private void initializefields() {

        

    }
}