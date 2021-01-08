package com.pratikum19.jobhunter.ActiveUser.Employer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.pratikum19.jobhunter.API.APIConnection;
import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.AddJobRespone;
import com.pratikum19.jobhunter.DataModel.TalentResponse.RequestJobResponse;
import com.pratikum19.jobhunter.R;

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
                        sendNotif(Token,getNama);
                        Intent intent = new Intent(getBaseContext(), EmployerActivity.class);
                        startActivity(intent);

                    }

                    private void sendNotif(String token, String getNama) {
                        baseApiService.notif(token,getNama).enqueue(new Callback<RequestJobResponse>() {
                            @Override
                            public void onResponse(Call<RequestJobResponse> call, Response<RequestJobResponse> response) {
                                FirebaseMessaging.getInstance().subscribeToTopic("new-data");

                            }

                            @Override
                            public void onFailure(Call<RequestJobResponse> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<AddJobRespone> call, Throwable t) {

                    }
                });

            }
        });
    }
}