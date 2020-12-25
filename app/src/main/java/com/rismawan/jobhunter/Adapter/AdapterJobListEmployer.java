package com.rismawan.jobhunter.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rismawan.jobhunter.API.APIConnection;
import com.rismawan.jobhunter.API.BaseApiService;
import com.rismawan.jobhunter.ActiveUser.Employer.EmployerUpdateJob;
import com.rismawan.jobhunter.DataModel.EmployerResponse.DeleteJobResponse;
import com.rismawan.jobhunter.DataModel.EmployerResponse.JobList;
import com.rismawan.jobhunter.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterJobListEmployer extends RecyclerView.Adapter<AdapterJobListEmployer.HolderDataJob> {

    private final Context context;
    private List<JobList> jobListResponses;
    private String token;
    private BaseApiService baseApiService;

    public AdapterJobListEmployer(Context context, List<JobList> jobListResponses, String token) {
        this.context = context;
        this.jobListResponses = jobListResponses;
        this.token = token;
    }

    @NonNull
    @Override
    public HolderDataJob onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employer_list_job, parent,false);
        HolderDataJob holderDataJob = new HolderDataJob(view);
        return holderDataJob;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataJob holder, int position) {
        JobList jobList = jobListResponses.get(position) ;
        holder.idJob.setText(String.valueOf(jobList.getId()));
        holder.namaPt.setText(jobList.getNamaInstansi());
        holder.bagianPt.setText(jobList.getBagian());

        holder.actionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.action_data_job);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.actionUpdateJob:
                                Intent updateJob = new Intent(v.getContext(), EmployerUpdateJob.class);
                                updateJob.putExtra("idJob",String.valueOf(jobList.getId()));
                                updateJob.putExtra("namaJob", jobList.getNamaInstansi());
                                updateJob.putExtra("alamatJob",jobList.getAlamat());
                                updateJob.putExtra("bagianJob",jobList.getBagian());
                                updateJob.putExtra("syaratJob",jobList.getSyarat());
                                context.startActivity(updateJob);
                                ((Activity)context).finish();
                                break;
                            case R.id.actionDeleteJob:
                                baseApiService = APIConnection.getApiService();
                                baseApiService.deleteJob(token,jobList.getId()).enqueue(new Callback<DeleteJobResponse>() {
                                    @Override
                                    public void onResponse(Call<DeleteJobResponse> call, Response<DeleteJobResponse> response) {
                                        Toast.makeText(v.getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<DeleteJobResponse> call, Throwable t) {
                                        Toast.makeText(v.getContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                                    }
                                });


                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobListResponses.size();
    }

    public class HolderDataJob extends RecyclerView.ViewHolder {
        TextView namaPt,bagianPt,idJob;
        Button btn_detail;
        ImageButton actionMenu;

        public HolderDataJob(@NonNull View itemView) {
            super(itemView);
            idJob = itemView.findViewById(R.id.listIdjob);
            namaPt = itemView.findViewById(R.id.listNamaPt);
            bagianPt = itemView.findViewById(R.id.listBagian);
            actionMenu = itemView.findViewById(R.id.actionJob);
        }
    }


}
