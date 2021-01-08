package com.pratikum19.jobhunter.ActiveUser.Employer.Fragement;

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

import com.pratikum19.jobhunter.API.APIConnection;
import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.Adapter.AdapterApplyJob;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.ApplyJob.DetailDataJob;
import com.pratikum19.jobhunter.DataModel.EmployerResponse.ApplyJob.ReqJobResponse;
import com.pratikum19.jobhunter.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApplyJob extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DetailDataJob> detailDataJobs = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.applyList);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        retrieveData();
    }

    private void retrieveData() {
        SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
        String Token = getToken.getString("token", "");
        BaseApiService baseApiService = APIConnection.getApiService();
        baseApiService.reqJobTalent(Token).enqueue(new Callback<ReqJobResponse>() {
            @Override
            public void onResponse(Call<ReqJobResponse> call, Response<ReqJobResponse> response) {
                detailDataJobs = response.body().getDetailDataJob();
                adapter = new AdapterApplyJob(getContext(), detailDataJobs,Token);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ReqJobResponse> call, Throwable t) {

            }
        });
    }
}