package com.example.budgetifier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnView;
    HomeFragment homeFragment=new HomeFragment();
    BudgetFragment budgetFragment=new BudgetFragment();
    StatisticsFragment statisticsFragment=new StatisticsFragment();
    ProfileFragment profileFragment=new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnView = (BottomNavigationView) findViewById(R.id.bnView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.budget:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,budgetFragment).commit();
                        return true;
                    case R.id.statistics:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,statisticsFragment).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,profileFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}