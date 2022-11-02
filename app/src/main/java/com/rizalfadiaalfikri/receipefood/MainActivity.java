package com.rizalfadiaalfikri.receipefood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rizalfadiaalfikri.receipefood.Screens.Home.HomeActivity;
import com.rizalfadiaalfikri.receipefood.Screens.SigninSignup.SignInActivity;
import com.rizalfadiaalfikri.receipefood.Screens.SigninSignup.SignUpActivity;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

public class MainActivity extends AppCompatActivity {

    private Button btn_signIn, btn_signUp, btn_skip;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager= new SessionManager(MainActivity.this);

        initializefields();

    }

    private void initializefields() {

        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signUp = findViewById(R.id.btn_signUp);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (sessionManager.isLogin()) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {

        }

    }
}