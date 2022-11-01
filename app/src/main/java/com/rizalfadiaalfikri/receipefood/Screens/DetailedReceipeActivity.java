package com.rizalfadiaalfikri.receipefood.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.DetailedIngredientsFragment;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.DetailedStepsFragment;
import com.rizalfadiaalfikri.receipefood.Utils.Adapter.FragmentAdapter;

public class DetailedReceipeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    String receipe_name, receipe_ingredeients, receipe_stpes;
    TextView txt_receipe_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_receipe);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.main_tabs_page);

        tabLayout.setupWithViewPager(viewPager);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        fragmentAdapter.addFragment(new DetailedIngredientsFragment(), "Ingredients");
        fragmentAdapter.addFragment(new DetailedStepsFragment(), "Steps");
        viewPager.setAdapter(fragmentAdapter);

        txt_receipe_name = findViewById(R.id.txt_receipe_name_details);

        receipe_name = getIntent().getStringExtra("name").toString();
        receipe_ingredeients = getIntent().getStringExtra("ingredients").toString();
        receipe_stpes = getIntent().getStringExtra("steps");

        txt_receipe_name.setText(receipe_name);

        Log.d("INGREDIENTS", receipe_ingredeients);

    }
}