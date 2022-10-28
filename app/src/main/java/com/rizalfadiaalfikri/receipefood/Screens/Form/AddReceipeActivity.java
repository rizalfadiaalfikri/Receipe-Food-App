package com.rizalfadiaalfikri.receipefood.Screens.Form;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Receipes;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReceipeActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    EditText ed_receipe_name, ed_receipe_ingredients, ed_receipe_steps;
    Button btn_add_receipe;

    SessionManager sessionManager;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipe);

        apiInterface = ApiClient.getClientApi().create(ApiInterface.class);
        sessionManager = new SessionManager(getApplicationContext());

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        initializefields();

    }

    private void initializefields() {

        ed_receipe_name = findViewById(R.id.ed_receipe_name);
        ed_receipe_ingredients = findViewById(R.id.ed_receipe_ingredients);
        ed_receipe_steps = findViewById(R.id.ed_receipe_steps);
        btn_add_receipe = findViewById(R.id.btn_add_receipe);

        btn_add_receipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewReceipe();
            }
        });

    }

    private void addNewReceipe() {
        String user_id = sessionManager.getUserId();
        String receipe_name = ed_receipe_name.getText().toString();
        String receipe_ingredients = ed_receipe_ingredients.getText().toString();
        String receipe_steps = ed_receipe_steps.getText().toString();

        Log.d("USERS", user_id);


        if (receipe_name.isEmpty()) {
            ed_receipe_name.setError("Please required name");
        } else if (receipe_ingredients.isEmpty()) {
            ed_receipe_ingredients.setError("Please required ingredients");
        } else if (receipe_steps.isEmpty()) {
            ed_receipe_steps.setError("Please required steps");
        } else {
            ProgressDialog dialog = new ProgressDialog(AddReceipeActivity.this);
            dialog.setTitle("Adding new receipes ......");
            dialog.setMessage("please wait");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            Call<Receipes> call = apiInterface.addReceipes(Integer.parseInt(user_id), receipe_name, receipe_ingredients, receipe_steps);
            call.enqueue(new Callback<Receipes>() {
                @Override
                public void onResponse(Call<Receipes> call, Response<Receipes> response) {
                    if (response.body().getResponse().equals("ok")) {
                        Toast.makeText(AddReceipeActivity.this, "Add new receipe is successfull", Toast.LENGTH_SHORT).show();
                        Log.d("RECEIPE", "SUCCESSFULL");
                        dialog.dismiss();
                    } else if (response.body().getResponse().equals("failed")) {
                        Toast.makeText(AddReceipeActivity.this, "Add new recipe is failed", Toast.LENGTH_SHORT).show();
                        Log.d("RECEIPE", "FAILED");
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Receipes> call, Throwable t) {

                }
            });
        }


    }
}