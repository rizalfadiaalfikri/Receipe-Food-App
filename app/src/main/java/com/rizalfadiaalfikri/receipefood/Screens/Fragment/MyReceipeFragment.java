package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Form.AddReceipeActivity;

public class MyReceipeFragment extends Fragment {

    FloatingActionButton fab_add;

    public MyReceipeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_receipe, container, false);

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
}