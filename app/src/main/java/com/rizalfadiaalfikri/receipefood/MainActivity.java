package com.rizalfadiaalfikri.receipefood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rizalfadiaalfikri.receipefood.Screens.Home.HomeActivity;
import com.rizalfadiaalfikri.receipefood.Screens.SigninSignup.SignInActivity;
import com.rizalfadiaalfikri.receipefood.Screens.SigninSignup.SignUpActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_signIn, btn_signUp, btn_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializefields();

    }

    private void initializefields() {

        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signUp = findViewById(R.id.btn_signUp);
        btn_skip = findViewById(R.id.btn_skip);

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

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}