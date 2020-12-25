package com.rismawan.jobhunter.ActiveUser.Employer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rismawan.jobhunter.API.APIConnection;
import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.ActiveUser.Employer.Fragement.OpeningJob;
import com.rismawan.jobhunter.ActiveUser.Talent.TalentActivity;
import com.rismawan.jobhunter.Auth.Login;
import com.rismawan.jobhunter.Auth.Registrasi;
import com.rismawan.jobhunter.DataModel.EmployerResponse.AddJobRespone;
import com.rismawan.jobhunter.MainActivity;
import com.rismawan.jobhunter.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployerAddJob extends AppCompatActivity {

    private BaseApiService baseApiService;
    private String getNama, getAlamat,getBagian,getSyarat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_add_job);

        Button addJob = findViewById(R.id.btnAddJob);

        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseApiService = APIConnection.getApiService();

                EditText nama = findViewById(R.id.inNama);
                EditText alamat = findViewById(R.id.inAlamat);
                EditText bagian = findViewById(R.id.inBagian);
                EditText syarat = findViewById(R.id.inSyarat);

                getNama = nama.getText().toString();
                getAlamat = alamat.getText().toString();
                getBagian = bagian.getText().toString();
                getSyarat = syarat.getText().toString();

                jobAdd(getNama,getAlamat,getBagian,getSyarat);


            }

            private void jobAdd(String getNama, String getAlamat, String getBagian, String getSyarat) {
                SharedPreferences getToken = EmployerAddJob.this.getSharedPreferences("SESSION", getBaseContext().MODE_PRIVATE);
                String Token = getToken.getString("token", "");
                baseApiService.createJob(Token,getNama,getAlamat,getBagian,getSyarat).enqueue(new Callback<AddJobRespone>() {
                    @Override
                    public void onResponse(Call<AddJobRespone> call, Response<AddJobRespone> response) {
                        Toast.makeText(EmployerAddJob.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), EmployerActivity.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<AddJobRespone> call, Throwable t) {

                    }
                });

            }
        });
    }
}