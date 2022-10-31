package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizalfadiaalfikri.receipefood.R;

public class DetailedStepsFragment extends Fragment {


    public DetailedStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_steps, container, false);
        return view;
    }
}