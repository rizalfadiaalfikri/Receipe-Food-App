package com.rizalfadiaalfikri.receipefood.Screens;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.tabs.TabLayout;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.DetailedIngredientsFragment;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.DetailedStepsFragment;
import com.rizalfadiaalfikri.receipefood.Utils.Adapter.FragmentAdapter;

public class DetailedReceipeActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    String receipe_name, receipe_images;
    TextView txt_receipe_name;
    ImageView image_receipe_details;

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
        image_receipe_details = findViewById(R.id.image_receipe_details);

        receipe_name = getIntent().getStringExtra("name").toString();
        receipe_images = getIntent().getStringExtra("images").toString();

        txt_receipe_name.setText(receipe_name);
        Glide.with(this)
                .load(receipe_images)
                .error(R.drawable.model_image)
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
                .into(image_receipe_details);

    }

    public void onCustomToogleClick(View view) {

    }
}