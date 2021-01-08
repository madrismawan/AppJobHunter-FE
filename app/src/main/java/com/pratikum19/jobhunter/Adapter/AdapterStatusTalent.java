package com.pratikum19.jobhunter.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pratikum19.jobhunter.API.BaseApiService;
import com.pratikum19.jobhunter.DataModel.TalentResponse.DataJob;
import com.pratikum19.jobhunter.DataModel.TalentResponse.DetailJob;
import com.pratikum19.jobhunter.R;

import java.util.List;

public class AdapterStatusTalent extends RecyclerView.Adapter<AdapterStatusTalent.HolderDataJob> {

    private final Context context;
    private List<DetailJob> getDetailJob;
    private String token;
    private BaseApiService baseApiService;

    public AdapterStatusTalent(Context context, List<DetailJob> getDetailJob, String token) {
        this.context = context;
        this.getDetailJob = getDetailJob;
        this.token = token;
    }

    @NonNull
    @Override
    public AdapterStatusTalent.HolderDataJob onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_status, parent,false);
        AdapterStatusTalent.HolderDataJob holderDataJob = new AdapterStatusTalent.HolderDataJob(view);
        return  holderDataJob;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataJob holder, int position) {
        DetailJob dataJob = getDetailJob.get(position);
        holder.namaPt.setText(dataJob.getJoblist().getNamaInstansi());
        holder.status.setText(dataJob.getStatus());
    }

    @Override
    public int getItemCount() {
        return getDetailJob.size();
    }

    public class HolderDataJob extends RecyclerView.ViewHolder {
        TextView namaPt,status;

        public HolderDataJob(@NonNull View itemView) {
            super(itemView);
            namaPt = itemView.findViewById(R.id.namaPt);
            status = itemView.findViewById(R.id.statusTalent);
        }
    }
}
