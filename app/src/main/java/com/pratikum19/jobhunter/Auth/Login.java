package com.pratikum19.jobhunter.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pratikum19.jobhunter.API.APIConnection;
import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.ActiveUser.Employer.EmployerActivity;
import com.pratikum19.jobhunter.ActiveUser.Talent.TalentActivity;
import com.pratikum19.jobhunter.DataModel.LoginResponse.LoginRespone;
import com.pratikum19.jobhunter.R;
import com.pratikum19.jobhunter.Session.SessionSave;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Login extends AppCompatActivity {

    private BaseApiService baseApiService;
    private String getEmail, getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnLogin(View view) {
        baseApiService = APIConnection.getApiService();
        EditText username = (EditText) findViewById(R.id.inUsername);
        EditText password = (EditText) findViewById(R.id.inPassword);
        getEmail = username.getText().toString();
        getPassword = password.getText().toString();

        login(getEmail,getPassword);

    }

    private void login(String getEmail, String getPassword) {
        baseApiService.login(getEmail,getPassword).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                SessionSave.saveSession(getBaseContext(),response.body().getAccess_token(),response.body().getUser().getRole());
                Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                if (response.body().getUser().getRole().equals("Employer")){
                    Intent intent = new Intent(Login.this, EmployerActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent = new Intent(Login.this, TalentActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                Toast.makeText(Login.this, "Gagal Menghubungi Server" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
