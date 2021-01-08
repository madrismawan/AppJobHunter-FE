package com.pratikum19.jobhunter.ActiveUser.Talent.Fragment;

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
import com.pratikum19.jobhunter.Adapter.AdapterJobList;
import com.pratikum19.jobhunter.DataModel.TalentResponse.DataJob;
import com.pratikum19.jobhunter.DataModel.TalentResponse.GetDataJobResponse;
import com.pratikum19.jobhunter.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobListTalentFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DataJob> getDataJobResponse = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_list_talent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.listJobTalent);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        retrieveData();

    }

    private void retrieveData() {
        SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
        String Token = getToken.getString("token", "");
        BaseApiService baseApiService = APIConnection.getApiService();
        baseApiService.getDataJob(Token).enqueue(new Callback<GetDataJobResponse>() {
            @Override
            public void onResponse(Call<GetDataJobResponse> call, Response<GetDataJobResponse> response) {
                getDataJobResponse = response.body().getDataJob();
                adapter = new AdapterJobList(getContext(), getDataJobResponse,Token);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<GetDataJobResponse> call, Throwable t) {

            }
        });

    }
}