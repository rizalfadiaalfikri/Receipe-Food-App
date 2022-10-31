package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Utils.Adapter.ReceipesAdapter;
import com.rizalfadiaalfikri.receipefood.Utils.Model.Receipes;
import com.rizalfadiaalfikri.receipefood.Utils.Model.ReceipesModel;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiClient;
import com.rizalfadiaalfikri.receipefood.Utils.RetrofitApi.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    List<ReceipesModel> receipes;
    ReceipesAdapter receipesAdapter;

    ApiInterface apiInterface;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        apiInterface = ApiClient.getClientApi().create(ApiInterface.class);

        recyclerView = view.findViewById(R.id.recyclerView_home);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchReceipes();

        return view;
    }

    private void fetchReceipes() {

        Call<Receipes> call = apiInterface.fetchReceipes();
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