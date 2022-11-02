package com.rizalfadiaalfikri.receipefood.Screens.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Utils.Session.SessionManager;

import static android.content.Context.MODE_PRIVATE;

public class DetailedIngredientsFragment extends Fragment {

    private TextView txt_ingredeients;
    public static String KEY_INGREDIENTS = "ingredeients";

    public DetailedIngredientsFragment() {
        // Required empty public constructor
    }

    Activity activity;
    int PRIVATE_MODE = 0;
    SessionManager sessionManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detailed_ingredients, container, false);

        txt_ingredeients = view.findViewById(R.id.txt_ingredeients);

//        Bundle bundle = getArguments();
//        String receipe_ingredients = bundle.getString("ingredients");
//        txt_ingredeients.setText(receipe_ingredients);

        sessionManager = new SessionManager(getContext());

        txt_ingredeients.setText(sessionManager.getIngredients());

        return  view;
    }
}