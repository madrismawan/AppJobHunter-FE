package com.rismawan.jobhunter.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rismawan.jobhunter.API.APIConnection;
import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.DataModel.RegisterResponse.RegisRespone;
import com.rismawan.jobhunter.MainActivity;
import com.rismawan.jobhunter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registrasi extends AppCompatActivity {
    private BaseApiService baseApiService;
    private String getNama,getEmail,getPassword,getCpassword,getRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);

        Spinner spinner = findViewById(R.id.regRole);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getBaseContext(),R.array.role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void btnRegis(View view) {
        baseApiService = APIConnection.getApiService();
        EditText nama = findViewById(R.id.regNama);
        EditText email = findViewById(R.id.regEmail);
        EditText password = findViewById(R.id.regPassword);
        EditText cpassword = findViewById(R.id.regPasswordConfirm);
        Spinner role = findViewById(R.id.regRole);

        getNama = nama.getText().toString();
        getEmail = email.getText().toString();
        getPassword = password.getText().toString();
        getCpassword = cpassword.getText().toString();
        getRole = role.getSelectedItem().toString();

        regisUser(getNama,getEmail,getPassword,getCpassword,getRole);

    }

    private void regisUser(String getNama, String getEmail, String getPassword, String getCpassword, String getRole) {
        baseApiService.registrasi(getNama, getEmail, getPassword, getCpassword, getRole).enqueue(new Callback<RegisRespone>() {
            @Override
            public void onResponse(Call<RegisRespone> call, Response<RegisRespone> response) {
                Toast.makeText(Registrasi.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<RegisRespone> call, Throwable t) {
                Toast.makeText(Registrasi.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}