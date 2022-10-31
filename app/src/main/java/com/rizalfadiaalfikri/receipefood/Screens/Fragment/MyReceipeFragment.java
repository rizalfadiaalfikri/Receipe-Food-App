package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Form.AddReceipeActivity;
import com.rizalfadiaalfikri.receipefood.Utils.Adapter.ReceipesAdapter;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Receipes;
import com.rizalfadiaalfikri.receipefood.Utils.Model.ReceipesModel;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReceipeFragment extends Fragment {

    FloatingActionButton fab_add;


    RecyclerView recyclerView;
    List<ReceipesModel> receipes;
    ReceipesAdapter receipesAdapter;

    ApiInterface apiInterface;

    SessionManager sessionManager;

    public MyReceipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_receipe, container, false);

        apiInterface = ApiClient.getClientApi().create(ApiInterface.class);

        recyclerView = view.findViewById(R.id.recycler_view_myReceipes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sessionManager = new SessionManager(getContext());

        fetchReceipes();

        fab_add = view.findViewById(R.id.fab_add_my_receipe);

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddReceipeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void fetchReceipes() {

        String users_id = sessionManager.getUserId();

        Call<Receipes> call = apiInterface.fetchMyReceipes(Integer.parseInt(users_id));
        call.enqueue(new Callback<Receipes>() {
            @Override
            public void onResponse(Call<Receipes> call, Response<Receipes> response) {

                receipes = response.body().getData();

                Log.d("Data", receipes.toString());

                receipesAdapter = new ReceipesAdapter(receipes, getContext());
                recyclerView.setAdapter(receipesAdapter);
                receipesAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<Receipes> call, Throwable t) {

            }
        });
    }


}