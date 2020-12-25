package com.rismawan.jobhunter.ActiveUser.Employer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rismawan.jobhunter.API.APIConnection;
import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.Auth.Login;
import com.rismawan.jobhunter.DataModel.EmployerResponse.ApplyJob.DetailDataJob;
import com.rismawan.jobhunter.DataModel.EmployerResponse.KonfirmasiJob.KonfirmasiResponse;
import com.rismawan.jobhunter.DataModel.TalentResponse.DataDetailJob;
import com.rismawan.jobhunter.DataModel.TalentResponse.RequestJobResponse;
import com.rismawan.jobhunter.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiJob extends AppCompatActivity {

    TextView namaInstansi,bagianJob,AlamatJob,namaPelamar,noTlpn,umur,gender;
    Button btnAcc,btnDeceline;
    private List<DetailDataJob> getDataDetailJob = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_job);

        SharedPreferences getToken = KonfirmasiJob.this.getSharedPreferences("SESSION", getBaseContext().MODE_PRIVATE);
        String Token = getToken.getString("token", "");
        String getidJob = getIntent().getStringExtra("idJob");

        getData(Token,getidJob);

        btnAcc = findViewById(R.id.btnAccept);
        btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String success = "Accept";
                BaseApiService baseApiService = APIConnection.getApiService();
                baseApiService.applyJob(Token,getidJob,success).enqueue(new Callback<RequestJobResponse>() {
                    @Override
                    public void onResponse(Call<RequestJobResponse> call, Response<RequestJobResponse> response) {
                        Toast.makeText(KonfirmasiJob.this, "Berhasil Menirima Pegawai", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KonfirmasiJob.this, EmployerActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<RequestJobResponse> call, Throwable t) {
                        Toast.makeText(KonfirmasiJob.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnDeceline = findViewById(R.id.btnReject);
        btnDeceline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deceline = "Deceline";
                BaseApiService baseApiService = APIConnection.getApiService();
                baseApiService.applyJob(Token,getidJob,deceline).enqueue(new Callback<RequestJobResponse>() {
                    @Override
                    public void onResponse(Call<RequestJobResponse> call, Response<RequestJobResponse> response) {
                        Toast.makeText(KonfirmasiJob.this, "Berhasil Menolak Talent", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(KonfirmasiJob.this, EmployerActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<RequestJobResponse> call, Throwable t) {
                        Toast.makeText(KonfirmasiJob.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }

    private void getData(String token, String getidJob) {
        BaseApiService baseApiService = APIConnection.getApiService();
        baseApiService.detailPelamar(token,getidJob).enqueue(new Callback<KonfirmasiResponse>() {
            @Override
            public void onResponse(Call<KonfirmasiResponse> call, Response<KonfirmasiResponse> response) {
                namaInstansi = findViewById(R.id.namaJob);
                bagianJob = findViewById(R.id.bagianJob);
                AlamatJob = findViewById(R.id.alamatJob);

                namaPelamar = findViewById(R.id.namaPelamar);
                noTlpn = findViewById(R.id.notlpnPemalamar);
                umur = findViewById(R.id.umurPelamar);
                gender = findViewById(R.id.genderPelamar);

                DetailDataJob dataDetailJob = response.body().getDetailData().get(0);
                namaInstansi.setText(dataDetailJob.getDataJoblist().getNamaInstansi());
                bagianJob.setText(dataDetailJob.getDataJoblist().getBagian());
                AlamatJob.setText(dataDetailJob.getDataJoblist().getAlamat());
                namaPelamar.setText(dataDetailJob.getUserDataJob().getName());
                noTlpn.setText(dataDetailJob.getUserDataJob().getNotlpn());
                umur.setText(dataDetailJob.getUserDataJob().getUmur());
                gender.setText(dataDetailJob.getUserDataJob().getGender());


            }

            @Override
            public void onFailure(Call<KonfirmasiResponse> call, Throwable t) {

            }
        });

    }
}