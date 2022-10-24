package com.rizalfadiaalfikri.receipefood.Screens.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.HomeFragment;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.MyReceipeFragment;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        replaceFragment(new HomeFragment());
        bottomNavigation();

    }

    private void bottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        replaceFragment(new HomeFragment());
                    case R.id.menu_my_receipe:
                        replaceFragment(new MyReceipeFragment());
                    case R.id.menu_favorite:
                        replaceFragment(new HomeFragment());
                    case R.id.menu_profile:
                        replaceFragment(new ProfileFragment());
                }

                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}