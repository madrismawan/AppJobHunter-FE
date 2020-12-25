package com.rismawan.jobhunter.ActiveUser.Employer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rismawan.jobhunter.ActiveUser.Employer.Fragement.HomeFragment;
import com.rismawan.jobhunter.ActiveUser.Employer.Fragement.OpeningJob;
import com.rismawan.jobhunter.ActiveUser.Employer.Fragement.ApplyJob;
import com.rismawan.jobhunter.R;

public class EmployerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer);
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(EmployerActivity.this);
        loadFragment(new OpeningJob());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_Opening:
                fragment = new OpeningJob();
                break;

            case R.id.navigation_Acc:
                fragment = new ApplyJob();
                break;
        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragementContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}