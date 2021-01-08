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
import com.pratikum19.jobhunter.Adapter.AdapterStatusTalent;
import com.pratikum19.jobhunter.DataModel.TalentResponse.DataJob;
import com.pratikum19.jobhunter.DataModel.TalentResponse.DetailJob;
import com.pratikum19.jobhunter.DataModel.TalentResponse.RequestJobResponse;
import com.pratikum19.jobhunter.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StatusTalentFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<DetailJob> getDetailJob = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_status_talent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.statusTalent);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        retrieveData();

    }

    public void retrieveData() {
        SharedPreferences getToken = getActivity().getSharedPreferences("SESSION", getActivity().MODE_PRIVATE);
        String Token = getToken.getString("token", "");
        BaseApiService baseApiService = APIConnection.getApiService();
        baseApiService.statusTalent(Token).enqueue(new Callback<RequestJobResponse>() {
            @Override
            public void onResponse(Call<RequestJobResponse> call, Response<RequestJobResponse> response) {
                getDetailJob = response.body().getDetailJob();
                adapter = new AdapterStatusTalent(getContext(), getDetailJob,Token);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<RequestJobResponse> call, Throwable t) {

            }
        });


    }
}