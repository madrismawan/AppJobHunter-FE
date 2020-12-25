package com.rismawan.jobhunter.ActiveUser.Talent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rismawan.jobhunter.API.APIConnection;
import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.ActiveUser.Employer.EmployerActivity;
import com.rismawan.jobhunter.ActiveUser.Employer.EmployerAddJob;
import com.rismawan.jobhunter.Auth.Login;
import com.rismawan.jobhunter.DataModel.TalentResponse.DataDetailJob;
import com.rismawan.jobhunter.DataModel.TalentResponse.DataJob;
import com.rismawan.jobhunter.DataModel.TalentResponse.DetailJobResponse;
import com.rismawan.jobhunter.DataModel.TalentResponse.RequestJobResponse;
import com.rismawan.jobhunter.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestJobTalent extends AppCompatActivity {

    private Button btnLamar;
    private TextView reqNamaPt,reqBagian,reqEmail,reqAlamat,reqSyarat;
    private EditText pengalaman;


    private BaseApiService baseApiService;
    private List<DataDetailJob> getDataDetailJob = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_job_talent);

        String getIdJob = getIntent().getStringExtra("idJob");
        SharedPreferences getToken = RequestJobTalent.this.getSharedPreferences("SESSION", getBaseContext().MODE_PRIVATE);
        String Token = getToken.getString("token", "");

        getData(Token,getIdJob);

        Button btnLamar = findViewById(R.id.btnLamaran);
        btnLamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText lamaran = findViewById(R.id.requestPengalaman);
                String getLamaran = lamaran.getText().toString();
                baseApiService = APIConnection.getApiService();
                baseApiService.lamarJob(Token,getIdJob,getLamaran).enqueue(new Callback<RequestJobResponse>() {
                    @Override
                    public void onResponse(Call<RequestJobResponse> call, Response<RequestJobResponse> response) {
                        Toast.makeText(RequestJobTalent.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RequestJobTalent.this, TalentActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<RequestJobResponse> call, Throwable t) {
                        Toast.makeText(RequestJobTalent.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    private void getData(String token, String getIdJob) {
        baseApiService = APIConnection.getApiService();
        baseApiService.detailDataJob(token,getIdJob).enqueue(new Callback<DetailJobResponse>() {
            @Override
            public void onResponse(Call<DetailJobResponse> call, Response<DetailJobResponse> response) {
                reqNamaPt = findViewById(R.id.requestNama);
                reqBagian = findViewById(R.id.requestBagian);
                reqAlamat = findViewById(R.id.requestAlamat);
                reqEmail = findViewById(R.id.requestEmail);
                reqSyarat = findViewById(R.id.requestSyarat);

                getDataDetailJob = response.body().getDetailDataJob();
                DataDetailJob dataDetailJob = getDataDetailJob.get(0);
                reqNamaPt.setText(dataDetailJob.getNamaInstansi());
                reqBagian.setText(dataDetailJob.getBagian());
                reqAlamat.setText(dataDetailJob.getAlamat());
                reqEmail.setText(dataDetailJob.getUser().getEmail());
                reqSyarat.setText(dataDetailJob.getSyarat());


            }

            @Override
            public void onFailure(Call<DetailJobResponse> call, Throwable t) {

            }
        });


    }


}