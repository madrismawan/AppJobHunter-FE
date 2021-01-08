package com.pratikum19.jobhunter.ActiveUser.Employer.Fragement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pratikum19.jobhunter.API.APIConnection;
import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.ActiveUser.Employer.EmployerAddJob;
import com.pratikum19.jobhunter.Adapter.AdapterJobListEmployer;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.JobList;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.JobListResponse;
import com.pratikum19.jobhunter.R;
import com.pratikum19.jobhunter.Room.Entity.EmployerJobList;
import com.pratikum19.jobhunter.Room.MyDatabase.MyDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OpeningJob extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<JobList> jobListResponses = new ArrayList<>();

    private MyDatabase myDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_opening_job, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.listJobEmployer);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        myDatabase = MyDatabase.createDatabase(getContext());

        retrieveData();

        FloatingActionButton addJob =(FloatingActionButton)view.findViewById(R.id.addJob);
        addJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmployerAddJob.class);
                startActivity(intent);
            }
        });



    }

    private void retrieveData() {
        SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
        String Token = getToken.getString("token", "");
        BaseApiService baseApiService = APIConnection.getApiService();
        baseApiService.getJobEmployer(Token).enqueue(new Callback<JobListResponse>() {
            @Override
            public void onResponse(Call<JobListResponse> call, Response<JobListResponse> response) {
                jobListResponses = response.body().getJobList();
                myDatabase.myDao().nukeTableEmployerJobList();
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        for(int x = 0;x <= jobListResponses.size()-1;x++){
                            EmployerJobList employerJobList = new EmployerJobList();
                            employerJobList.setId(jobListResponses.get(x).getId());
                            employerJobList.setNama_instansi(jobListResponses.get(x).getNamaInstansi());
                            employerJobList.setAlamat(jobListResponses.get(x).getAlamat());
                            employerJobList.setBagian(jobListResponses.get(x).getBagian());
                            employerJobList.setCreated_at(jobListResponses.get(x).getCreatedAt());
                            employerJobList.setUpdated_at(jobListResponses.get(x).getUpdatedAt());
                            employerJobList.setSyarat(jobListResponses.get(x).getSyarat());
                            myDatabase.myDao().insertJobList(employerJobList);
                            Log.d("db",String.valueOf(jobListResponses.get(x).getId()));
                        }
                    }
                });

                adapter = new AdapterJobListEmployer(getContext(), jobListResponses,Token);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JobListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "PERIKSA KONEKSI ANDA", Toast.LENGTH_SHORT).show();
                List<EmployerJobList> employerJobLists = myDatabase.myDao().getAllEmployerJobList();
                List<JobList> jobLists = new ArrayList<>();

                for(int y = 0; y <= employerJobLists.size()-1; y++){
                    JobList job = new JobList();
                    job.setId(employerJobLists.get(y).getId());
                    job.setNamaInstansi(employerJobLists.get(y).getNama_instansi());
                    job.setBagian(employerJobLists.get(y).getBagian());
                    jobLists.add(job);
                }

                adapter = new AdapterJobListEmployer(getContext(), jobLists,Token);
                recyclerView.setAdapter(adapter);

            }
        });

    }
}