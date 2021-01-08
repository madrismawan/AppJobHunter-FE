package com.pratikum19.jobhunter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pratikum19.jobhunter.ActiveUser.Employer.EmployerActivity;
import com.pratikum19.jobhunter.ActiveUser.Talent.TalentActivity;
import com.pratikum19.jobhunter.Auth.Login;
import com.pratikum19.jobhunter.Auth.Registrasi;
import com.pratikum19.jobhunter.FCM.TokenFCM;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Rismawan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences getToken = MainActivity.this.getSharedPreferences("SESSION", MainActivity.MODE_PRIVATE);
        String role = getToken.getString("role", "");

        FirebaseMessaging.getInstance().subscribeToTopic("new-data");

        TokenFCM tokenFCM = new TokenFCM();
        tokenFCM.onTokenRefresh();

        if (role.equals("Employer")){
            Intent intent = new Intent(MainActivity.this, EmployerActivity.class);
            startActivity(intent);
        }if(role.equals("Talent")){
            Intent intent = new Intent(MainActivity.this, TalentActivity.class);
            startActivity(intent);
        }


//        SharedPreferences getToken = MainActivity.this.getSharedPreferences("SESSION", MainActivity.MODE_PRIVATE);
//        String Token = getToken.getString("token", "");
//        Log.d(TAG, Token);
//        SessionSave sessionLogin = new SessionSave();
//        sessionLogin.deleteSession(getBaseContext());

    }

    public void btnToRegis(View view) {
        Intent intent = new Intent(getBaseContext(), Registrasi.class);
        startActivity(intent);
    }

    public void btnToLogin(View view) {
        Intent intent = new Intent(getBaseContext(), Login.class);
        startActivity(intent);
    }
}