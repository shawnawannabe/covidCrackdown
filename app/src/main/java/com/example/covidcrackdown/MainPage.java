package com.example.covidcrackdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, new HomePage()).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.homePage:
                        selectedFragment = new HomePage();
                        break;
                    case R.id.checkIn:
                        selectedFragment = new CheckIn();
                        break;
                    case R.id.userProfile:
                        selectedFragment = new UserProfile();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, selectedFragment).commit();
                return true;
            }
    };
}