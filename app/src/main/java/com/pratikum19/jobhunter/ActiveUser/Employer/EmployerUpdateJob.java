package com.pratikum19.jobhunter.ActiveUser.Employer;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pratikum19.jobhunter.API.APIConnection;
import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.UpdateJobResponse;
import com.pratikum19.jobhunter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployerUpdateJob extends AppCompatActivity {

    private BaseApiService baseApiService;
    private EditText nama,alamat,bagian,syarat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_update_job);
//        Toolbar toolbar = findViewById(R.id.toolbarUpdateJob);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EmployerUpdateJob.this.onBackPressed();
//            }
//        });

        nama = findViewById(R.id.updateJobNama);
        alamat = findViewById(R.id.updateJobAlamat);
        bagian = findViewById(R.id.updateJobBagian);
        syarat = findViewById(R.id.updateJobSyarat);


        String getNamaJob = getIntent().getStringExtra("namaJob");
        String getAlamatJob = getIntent().getStringExtra("alamatJob");
        String getBagianJob = getIntent().getStringExtra("bagianJob");
        String getSyaratJob = getIntent().getStringExtra("syaratJob");

        nama.setText(getNamaJob);
        alamat.setText(getAlamatJob);
        bagian.setText(getBagianJob);
        syarat.setText(getSyaratJob);

        Button btnUpdate = findViewById(R.id.btnupdateJob);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateData();
            }

            private void updateData() {
                String getIdJob = getIntent().getStringExtra("idJob");
                SharedPreferences getToken = EmployerUpdateJob.this.getSharedPreferences("SESSION", getBaseContext().MODE_PRIVATE);
                String Token = getToken.getString("token", "");
                EditText upnama = findViewById(R.id.updateJobNama);
                EditText upalamat = findViewById(R.id.updateJobAlamat);
                EditText upbagian = findViewById(R.id.updateJobBagian);
                EditText upsyarat = findViewById(R.id.updateJobSyarat);
                String nama = upnama.getText().toString();
                String alamat = upalamat.getText().toString();   
                String bagian = upbagian.getText().toString();
                String syarat = upsyarat.getText().toString();
                baseApiService = APIConnection.getApiService();

                baseApiService.updateJob(Token,getIdJob,nama,alamat,bagian,syarat).enqueue(new Callback<UpdateJobResponse>() {
                    @Override
                    public void onResponse(Call<UpdateJobResponse> call, Response<UpdateJobResponse> response) {
                        Toast.makeText(EmployerUpdateJob.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EmployerUpdateJob.this, EmployerActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<UpdateJobResponse> call, Throwable t) {
                        Toast.makeText(EmployerUpdateJob.this, "Tidak Terhubung ke Server", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }


}