package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Profile;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.Users;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    Toolbar mToolbar;
    ApiInterface apiInterface;

    private EditText ed_name, ed_email;
    private Button btn_update;
    private CircleImageView circleImageView;

    SessionManager sessionManager;

    private List<Profile> profileModelList;

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
        btn_update = view.findViewById(R.id.btn_update_profile);
        circleImageView = view.findViewById(R.id.profile_circleImage);

        String userId = sessionManager.getUserId();

        Call<Users> call = apiInterface.getProfile(Integer.parseInt(userId));
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful()) {
                    Log.d("NAME", response.body().getProfile().get(0).getUser_name());

                    String name = response.body().getProfile().get(0).getUser_name();
                    String email = response.body().getProfile().get(0).getUser_email();

                    ed_name.setText(name);
                    ed_email.setText(email);

                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });

        return view;
    }
}