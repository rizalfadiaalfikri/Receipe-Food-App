package com.rizalfadiaalfikri.receipefood.Screens.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rizalfadiaalfikri.receipefood.R;
import com.rizalfadiaalfikri.receipefood.Screens.Fragment.FavoriteFragment;
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
                        break;
                    case R.id.menu_my_receipe:
                        replaceFragment(new MyReceipeFragment());
                        break;
                    case R.id.menu_favorite:
                        replaceFragment(new FavoriteFragment());
                        break;
                    case R.id.menu_profile:
                        replaceFragment(new ProfileFragment());
                        break;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_signOut) {
            Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}