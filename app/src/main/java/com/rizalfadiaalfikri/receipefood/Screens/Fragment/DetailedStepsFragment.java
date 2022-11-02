package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

public class DetailedStepsFragment extends Fragment {

    SessionManager sessionManager;
    private TextView txt_receipe_ingredients;

    public DetailedStepsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_steps, container, false);

        txt_receipe_ingredients = view.findViewById(R.id.txt_receipe_steps);

        sessionManager = new SessionManager(getContext());

        txt_receipe_ingredients.setText(sessionManager.getSteps());

        return view;
    }
}