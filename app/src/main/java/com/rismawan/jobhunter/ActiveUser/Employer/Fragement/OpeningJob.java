package com.rismawan.jobhunter.ActiveUser.Employer.Fragement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rismawan.jobhunter.API.APIConnection;
import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.ActiveUser.Employer.EmployerAddJob;
import com.rismawan.jobhunter.Adapter.AdapterJobListEmployer;
import com.rismawan.jobhunter.DataModel.EmployerResponse.JobList;
import com.rismawan.jobhunter.DataModel.EmployerResponse.JobListResponse;
import com.rismawan.jobhunter.R;

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
                adapter = new AdapterJobListEmployer(getContext(), jobListResponses,Token);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JobListResponse> call, Throwable t) {

            }
        });

    }
}