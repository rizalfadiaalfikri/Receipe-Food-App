package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Form.AddReceipeActivity;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Profile;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.Users;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    Toolbar mToolbar;
    ApiInterface apiInterface;

    private EditText ed_name, ed_email, ed_phone;
    private Button btn_update;
    private CircleImageView circleImageView;

    SessionManager sessionManager;

    private List<Profile> profileModelList;

    private static final int IMAGE_REQUEST = 777;
    private Bitmap bitmap;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        apiInterface = ApiClient.getClientApi().create(ApiInterface.class);

        sessionManager = new SessionManager(getContext());

        ed_name = view.findViewById(R.id.profile_ed_name);
        ed_email = view.findViewById(R.id.profile_ed_email);
        ed_phone = view.findViewById(R.id.profile_ed_phone_number);
        btn_update = view.findViewById(R.id.btn_update_profile);
        circleImageView = view.findViewById(R.id.profile_circleImage);

        String userId = sessionManager.getUserId();

        Call<Users> call = apiInterface.getProfile(Integer.parseInt(userId));
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {

                    String name = response.body().getProfile().get(0).getUser_name();
                    String email = response.body().getProfile().get(0).getUser_email();
                    String phone = response.body().getProfile().get(0).getUser_phone();
                    String profile_images = response.body().getProfile().get(0).getUser_profile();

                    ed_name.setText(name);
                    ed_email.setText(email);
                    ed_phone.setText(phone);

                    Glide.with(getContext().getApplicationContext())
                            .load(profile_images)
                            .error(R.drawable.profile_image)
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    return false;
                                }
                            })
                            .into(circleImageView);

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), IMAGE_REQUEST);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data!= null) {
            Uri imageUri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imageUri);
                circleImageView.setImageBitmap(bitmap);
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

    private void updateProfile() {
        String user_id = sessionManager.getUserId();
        String user_name = ed_name.getText().toString();
        String user_phone = ed_phone.getText().toString();
        String user_profile = imageToString();

        Log.d("USERS", user_id);

        if (TextUtils.isEmpty(user_name)) {
            ed_name.setError("name is require");
        } else if (TextUtils.isEmpty(user_phone)) {
            ed_phone.setError("phone is required");
        } else {
            ProgressDialog dialog = new ProgressDialog(getContext());
            dialog.setTitle("Update Profile ......");
            dialog.setMessage("please wait");
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            Call<Users> call = apiInterface.updateProfile(Integer.parseInt(user_id), user_name, user_phone,user_profile);
            call.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if (response.isSuccessful()) {
                        Log.d("BODY", response.body().toString());
                        Toast.makeText(getContext(), "Update is successfull", Toast.LENGTH_SHORT).show();

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