package com.rizalfadiaalfikri.receipefood.Screens.Form;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.MyReceipeFragment;
import com.rizalfadiaalfikri.receipefood.Screens.Home.HomeActivity;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Receipes;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReceipeActivity extends AppCompatActivity {
    private Toolbar mToolbar;

    EditText ed_receipe_name, ed_receipe_ingredients, ed_receipe_steps, ed_receipe_duration;
    Button btn_add_receipe;
    ImageView imageView_receipe;

    SessionManager sessionManager;
    ApiInterface apiInterface;

    private static final int IMAGE_REQUEST = 777;
    private Bitmap bitmap;

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
        ed_receipe_duration = findViewById(R.id.ed_receipe_duration);
        btn_add_receipe = findViewById(R.id.btn_add_receipe);
        imageView_receipe = findViewById(R.id.image_receipe);


        btn_add_receipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewReceipe();
            }
        });

        imageView_receipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), IMAGE_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data!= null) {
            Uri imageUri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView_receipe.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void addNewReceipe() {
        String user_id = sessionManager.getUserId();
        String receipe_name = ed_receipe_name.getText().toString();
        String receipe_images = imageToString();
        String receipe_ingredients = ed_receipe_ingredients.getText().toString();
        String receipe_steps = ed_receipe_steps.getText().toString();
        String receipe_duration = ed_receipe_duration.getText().toString();

        Log.d("USERS", user_id);


        if (receipe_name.isEmpty()) {
            ed_receipe_name.setError("Please required name");
        } else if (receipe_ingredients.isEmpty()) {
            ed_receipe_ingredients.setError("Please required ingredients");
        } else if (receipe_steps.isEmpty()) {
            ed_receipe_steps.setError("Please required steps");
        } else if (receipe_duration.isEmpty()) {
            ed_receipe_duration.setError("Please required duration");
        } else {
            ProgressDialog dialog = new ProgressDialog(AddReceipeActivity.this);
            dialog.setTitle("Adding new receipes ......");
            dialog.setMessage("please wait");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            Call<Receipes> call = apiInterface.addReceipes(Integer.parseInt(user_id), receipe_name, receipe_images,receipe_ingredients, receipe_steps, receipe_duration);
            call.enqueue(new Callback<Receipes>() {
                @Override
                public void onResponse(Call<Receipes> call, Response<Receipes> response) {
                    if (response.body().getResponse().equals("ok")) {
                        Toast.makeText(AddReceipeActivity.this, "Add new receipe is successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddReceipeActivity.this, HomeActivity.class);
                        startActivity(intent);
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