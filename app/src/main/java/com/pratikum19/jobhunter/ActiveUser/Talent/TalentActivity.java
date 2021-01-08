package com.pratikum19.jobhunter.ActiveUser.Talent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pratikum19.jobhunter.ActiveUser.Talent.Fragment.HomeTalentFragment;
import com.pratikum19.jobhunter.ActiveUser.Talent.Fragment.JobListTalentFragment;
import com.pratikum19.jobhunter.ActiveUser.Talent.Fragment.StatusTalentFragment;
import com.pratikum19.jobhunter.R;

public class TalentActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent);

        BottomNavigationView navigation = findViewById(R.id.nav_viewTalent);
        navigation.setOnNavigationItemSelectedListener(TalentActivity.this);
        loadFragment(new JobListTalentFragment());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navProfileTalent:
                fragment = new HomeTalentFragment();
                break;

            case R.id.navJobTalent:
                fragment = new JobListTalentFragment();
                break;

            case R.id.navStatusJobTalent:
                fragment = new StatusTalentFragment();
                break;
        }

        return loadFragment(fragment);
    }

    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragementContainerTalent, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}